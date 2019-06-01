package love.sola.nfc.api.mifare.classic

import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.constants.MifareClassicCardType
import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.mifare.classic.data.Key
import love.sola.nfc.util.hexToByteArray
import love.sola.nfc.util.toHexString
import javax.smartcardio.Card
import javax.smartcardio.CardException
import javax.smartcardio.CommandAPDU
import javax.smartcardio.ResponseAPDU

/**
 * @author Sola
 */
class MifareClassic(private val card: Card) {

    val type = MifareClassicCardType.fromATR(card.atr)

    fun getUID(): ByteArray {
        val response = transmit(CommandAPDU(0xFF, 0xCA, 0x00, 0x00))
        if (response.sw != 0x9000) {
            throw CardException("Operation failed.")
        }
        return response.data
    }

    fun readBlock(index: Int): Block {
        val response = transmit(CommandAPDU(0xFF, 0xB0, 0x00, index, 16))
        if (response.sw != 0x9000) {
            throw CardException("Operation failed.")
        }
        return Block(response.data)
    }

    fun writeBlock(index: Int, block: Block): Boolean {
        return transmit(
            CommandAPDU(
                0xFF, 0xD6, 0x00, index,
                block.data()
            )
        ).sw == 0x9000
    }

    fun authBlock(key: Key, block: Int, type: KeyType): Boolean {
        return loadKey(key) && authKey(block, type)
    }

    fun loadKey(key: Key): Boolean {
        return transmit(
            CommandAPDU(
                0xFF, 0x82, 0x00, 0x00,
                key.data()
            )
        ).sw == 0x9000
    }

    fun authKey(block: Int, type: KeyType): Boolean {
        return transmit(
            CommandAPDU(
                0xFF, 0x86, 0x00, 0x00,
                byteArrayOf(0x01, 0x00, block.toByte(), type.code, 0x00)
            )
        ).sw == 0x9000
    }

    fun controlLED(P2: Int, T1: Int, T2: Int, count: Int, buzzer: Int) {
        val cmd = CommandAPDU(
            byteArrayOf(
                0xFF.toByte(),
                0x00,
                0x40,
                P2.toByte(),
                0x04,
                T1.toByte(),
                T2.toByte(),
                count.toByte(),
                buzzer.toByte()
            )
        )
        transmit(cmd)
    }

    fun modifyUID(uid: Block) {
        // dirty back door for chinese clone version
        transmit("D408630200630300")
        transmit("D442500057CD")
        transmit("D408633D07")
        transmit("D44240")
        transmit("D408633D00")
        transmit("D44243")
        transmit("D408630280630380") //Unlock
        transmit("D44001A000" + uid.data().toHexString())
    }

    @Throws(CardException::class)
    fun transmit(data: String): ResponseAPDU = transmit(data.hexToByteArray())

    @Throws(CardException::class)
    fun transmit(data: ByteArray): ResponseAPDU = transmit(CommandAPDU(0xFF, 0x00, 0x00, 0x00, data))

    @Throws(CardException::class)
    fun transmit(cmd: CommandAPDU): ResponseAPDU = card.basicChannel.transmit(cmd)

}
