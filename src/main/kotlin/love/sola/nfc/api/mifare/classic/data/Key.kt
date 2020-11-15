package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.util.hexToByteArray
import love.sola.nfc.util.toHexString
import java.io.Serializable

class Key(private val _data: ByteArray) : Cloneable, Serializable {

    constructor(data: String) : this(data.hexToByteArray())

    init {
        check(_data.size == 6) { "Key data must contains 6 bytes" }
    }

    companion object {
        val DEFAULT = Key(ByteArray(6) { 0xFF.toByte() })
    }

    fun data() = _data.clone()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Key

        if (!_data.contentEquals(other._data)) return false

        return true
    }

    override fun hashCode(): Int {
        return _data.contentHashCode()
    }

    override fun toString(): String {
        return "Key(${_data.toHexString()})"
    }
}
