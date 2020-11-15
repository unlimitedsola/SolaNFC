package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.api.mifare.classic.constants.MifareClassicCardType
import java.io.Serializable

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KeyChain

        if (!_data.contentEquals(other._data)) return false

        return true
    }

    override fun hashCode(): Int {
        return _data.contentHashCode()
    }

    override fun toString(): String {
        return "KeyChain(${_data.contentToString()})"
    }
}
