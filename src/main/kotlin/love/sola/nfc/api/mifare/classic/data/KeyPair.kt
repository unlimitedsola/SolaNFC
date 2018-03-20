package love.sola.nfc.api.mifare.classic.data

import java.io.Serializable
import java.util.*


data class KeyPair(val keyA: Key, val keyB: Key) : Cloneable, Serializable {

    companion object {
        val DEFAULT = KeyPair(Key.DEFAULT, Key.DEFAULT)
    }

    override fun toString(): String {
        return "KeyPair(keyA=$keyA, keyB=$keyB)"
    }

}
