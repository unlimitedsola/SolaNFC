package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.util.hexToByteArray
import love.sola.nfc.util.toHexString
import java.io.Serializable
import kotlin.experimental.inv

class AccessBits(private val _data: ByteArray) : Cloneable, Serializable {

    companion object {
        val DEFAULT = AccessBits("FF078069")
        fun Byte.lowerBits(): Int = this.toInt() and 0x0F
        fun Byte.higherBits(): Int = this.toInt() and 0xF0 shr 4
    }

    init {
        check(_data.size == 4) { "Access bits must contain 4 bytes" }
        check(_data[0].lowerBits() == _data[1].inv().higherBits()) { "The first 12 bits should be inverted." }
        check(_data[0].higherBits() == _data[2].inv().lowerBits()) { "The first 12 bits should be inverted." }
        check(_data[1].lowerBits() == _data[2].inv().higherBits()) { "The first 12 bits should be inverted." }
    }

    constructor(data: String) : this(data.hexToByteArray())

    fun data() = _data.clone()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccessBits

        if (!_data.contentEquals(other._data)) return false

        return true
    }

    override fun hashCode(): Int {
        return _data.contentHashCode()
    }

    override fun toString(): String {
        return "AccessBits(${_data.toHexString()})"
    }
}
