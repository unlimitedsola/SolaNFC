package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.api.mifare.classic.constants.MifareClassicCardType
import java.io.Serializable
import java.util.*


class KeyChain(private val _data: Array<KeyPair>) : Cloneable, Serializable {

    companion object {
        val DEFAULT_1K =
            KeyChain(Array(MifareClassicCardType.MIFARE_CLASSIC_1K.totalSectors) { KeyPair.DEFAULT })
        val DEFAULT_4K =
            KeyChain(Array(MifareClassicCardType.MIFARE_CLASSIC_4K.totalSectors) { KeyPair.DEFAULT })
    }

    fun data() = _data.clone()

    fun flatten(): List<Key> = _data.flatMap { listOf(it.keyA, it.keyB) }.distinct()

    operator fun get(index: Int): KeyPair = _data[index]

    override fun toString(): String {
        return "KeyChain(${Arrays.toString(_data)})"
    }

}
