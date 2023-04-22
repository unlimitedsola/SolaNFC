package love.sola.nfc.api.mifare.classic

import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.constants.MifareClassicCardType
import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.mifare.classic.data.Key
import love.sola.nfc.util.hexToByteArray
import love.sola.nfc.util.toHexString
import java.nio.ByteBuffer
import javax.smartcardio.Card
import javax.smartcardio.CardException
import javax.smartcardio.CommandAPDU
import javax.smartcardio.ResponseAPDU

class MifareClassic(private val card: Card) {

    val type = MifareClassicCardType.fromATR(card.atr)

    var unlocked = false
        private set

    fun getUID(): ByteArray {
        val response = transmit("FFCA000000")
        if (response.sw != 0x9000) {
            throw CardException("Operation failed.")
        }
        return response.data
    }

    fun readBlock(index: Int): Block {
        val response = if (unlocked) {
            transmitPn532("D4400130" + index.toByte().toHexString())
        } else {
            transmit(CommandAPDU(0xFF, 0xB0, 0x00, index, 16))
        }
        if (response.sw != 0x9000) {
            throw CardException("Operation failed.")
        }
        return if (unlocked) {
            Block(response.data.sliceArray(3 until response.data.size))
        } else {
            Block(response.data)
        }
    }

    fun writeBlock(index: Int, block: Block): Boolean {
        return if (unlocked) {
            transmitPn532("D44001A0" + index.toByte().toHexString() + block.data().toHexString())
        } else {
            transmit(
                CommandAPDU(
                    0xFF, 0xD6, 0x00, index,
                    block.data()
                )
            )
        }.sw == 0x9000
    }

    fun authBlock(index: Int, type: KeyType, key: Key): Boolean {
        return loadKey(key) && authKey(index, type)
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

    fun unlock() {
        // WriteRegister -> PN53X_REG_CIU_TxMode (0x6302) and RxMode(0x6303)
        // TxSpeed = 106 kbps , TxFraming = Mifare, CRC disable)
        transmitPn532("D408630200630300")
        // Halt
        transmitPn532("D442500057CD")
        // WriteRegister -> PN53X_REG_CIU_BitFraming (0x633D)
        // TxFraming = 14443B
        transmitPn532("D408633D07")
        // Send 0x40
        transmitPn532("D44240")
        // WriteRegister -> PN53X_REG_CIU_BitFraming (0x633D)
        // TxFraming = Mifare
        transmitPn532("D408633D00")
        // Send 0x43
        transmitPn532("D44243")
        // WriteRegister -> CIU_TxMode (0x6302)  RxMode(0x6303)
        // CRC-Enable = yes, Speed = 106 kbps, Framing = Mifare
        transmitPn532("D408630280630380")
        unlocked = true
    }

    @Throws(CardException::class)
    fun transmit(data: String): ResponseAPDU = transmit(data.hexToByteArray())

    @Throws(CardException::class)
    fun transmit(data: ByteArray): ResponseAPDU = transmit(CommandAPDU(data))

    @Throws(CardException::class)
    fun transmit(cmd: CommandAPDU): ResponseAPDU = card.basicChannel.transmit(cmd)

    @Throws(CardException::class)
    fun transmit(data: String, buffer: ByteBuffer): Int = transmit(data.hexToByteArray(), buffer)

    @Throws(CardException::class)
    fun transmit(data: ByteArray, buffer: ByteBuffer): Int =
        transmit(CommandAPDU(data), buffer)

    @Throws(CardException::class)
    fun transmit(cmd: CommandAPDU, buffer: ByteBuffer): Int =
        card.basicChannel.transmit(ByteBuffer.wrap(cmd.bytes), buffer)

    @Throws(CardException::class)
    fun transmitPn532(data: String): ResponseAPDU = transmitPn532(data.hexToByteArray())

    @Throws(CardException::class)
    fun transmitPn532(data: ByteArray): ResponseAPDU = transmitPn532(CommandAPDU(0xFF, 0x00, 0x00, 0x00, data))

    @Throws(CardException::class)
    fun transmitPn532(cmd: CommandAPDU): ResponseAPDU = card.basicChannel.transmit(cmd)

    @Throws(CardException::class)
    fun transmitPn532(data: String, buffer: ByteBuffer): Int = transmitPn532(data.hexToByteArray(), buffer)

    @Throws(CardException::class)
    fun transmitPn532(data: ByteArray, buffer: ByteBuffer): Int =
        transmitPn532(CommandAPDU(0xFF, 0x00, 0x00, 0x00, data), buffer)

    @Throws(CardException::class)
    fun transmitPn532(cmd: CommandAPDU, buffer: ByteBuffer): Int =
        card.basicChannel.transmit(ByteBuffer.wrap(cmd.bytes), buffer)
}
