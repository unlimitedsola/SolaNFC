package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.util.hexToByteArray
import love.sola.nfc.util.toHexString
import java.io.Serializable
import java.util.*

/**
 * @author Sola
 */
class Block(private val _data: ByteArray) : Cloneable, Serializable {

    companion object {
        val DEFAULT = Block(ByteArray(16) { 0 })
        val DEFAULT_TRAILER =
            Block(Key.DEFAULT.data() + AccessBits.DEFAULT.data() + Key.DEFAULT.data())
    }

    constructor(data: String) : this(data.hexToByteArray())

    init {
        check(_data.size == 16) { "Block data must contains 16 bytes" }
    }

    fun data() = _data.clone()

    val size get() = _data.size
    operator fun get(index: Int): Byte = _data[index]

    val isValidUID: Boolean
        get() = _data[4] == (_data[0].toInt() xor _data[1].toInt() xor _data[2].toInt() xor _data[3].toInt()).toByte()

    override fun toString(): String {
        return "Block(${_data.toHexString()})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Block

        if (!Arrays.equals(_data, other._data)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(_data)
    }

}
