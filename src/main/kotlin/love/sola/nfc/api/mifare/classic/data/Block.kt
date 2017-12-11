package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.util.hexToByteArray
import love.sola.nfc.util.toHexString
import java.io.Serializable

/**
 * @author Sola
 */
class Block(val data: ByteArray) : Cloneable, Serializable {

    constructor(data: String) : this(data.hexToByteArray())

    init {
        check(data.size == 16) { "Block data must contains 16 bytes" }
    }

    companion object {
        val DEFAULT = Block(ByteArray(16) { 0 })
        val DEFAULT_TRAILER = Block(Key.DEFAULT.data + AccessBits.DEFAULT.data + Key.DEFAULT.data)
    }

    val size get() = data.size
    operator fun get(index: Int): Byte = data[index]

    override fun toString(): String {
        return "Block(${data.toHexString()})"
    }

}