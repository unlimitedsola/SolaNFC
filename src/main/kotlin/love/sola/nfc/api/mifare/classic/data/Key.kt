package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.util.hexToByteArray
import java.io.Serializable

/**
 * @author Sola
 */
class Key(val data: ByteArray) : Cloneable, Serializable {

    constructor(data: String) : this(data.hexToByteArray())

    init {
        check(data.size == 6) { "Key data must contains 6 bytes" }
    }

    companion object {
        val DEFAULT = Key(ByteArray(6) { 0xFF.toByte() })
    }

}