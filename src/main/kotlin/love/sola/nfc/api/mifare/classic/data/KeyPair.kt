package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.api.mifare.classic.constants.KeyType
import java.io.Serializable

data class KeyPair(val keyA: Key, val keyB: Key) : Cloneable, Serializable {

    companion object {
        val DEFAULT = KeyPair(Key.DEFAULT, Key.DEFAULT)
    }

    operator fun get(type: KeyType) = when (type) {
        KeyType.A -> keyA
        KeyType.B -> keyB
    }

    override fun toString(): String {
        return "KeyPair(keyA=$keyA, keyB=$keyB)"
    }

}
