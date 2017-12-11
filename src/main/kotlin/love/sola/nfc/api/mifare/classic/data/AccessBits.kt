package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.util.hexToByteArray
import java.io.Serializable
import kotlin.experimental.inv

/**
 * @author Sola
 */
class AccessBits(val data: ByteArray) : Cloneable, Serializable {

    companion object {
        val DEFAULT = AccessBits("FF078069")
        fun Byte.lowerBits(): Int = this.toInt() and 0x0F
        fun Byte.higherBits(): Int = this.toInt() and 0xF0 shr 4
    }

    init {
        check(data.size == 4) { "Access bits must contains 4 bytes" }
        check(data[0].lowerBits() == data[1].inv().higherBits()) { "The first 12 bits should be inverted." }
        check(data[0].higherBits() == data[2].inv().lowerBits()) { "The first 12 bits should be inverted." }
        check(data[1].lowerBits() == data[2].inv().higherBits()) { "The first 12 bits should be inverted." }
    }

    constructor(data: String) : this(data.hexToByteArray())

}
