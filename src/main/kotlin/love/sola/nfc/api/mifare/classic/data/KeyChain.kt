package love.sola.nfc.api.mifare.classic.data

import love.sola.nfc.api.mifare.classic.constants.MifareClassicCardType
import java.io.Serializable
import java.util.*


class KeyChain(val data: Array<KeyPair>) : Cloneable, Serializable {

    companion object {
        val DEFAULT_1K = KeyChain(Array(MifareClassicCardType.MIFARE_CLASSIC_1K.sectorsCount) { KeyPair.DEFAULT })
        val DEFAULT_4K = KeyChain(Array(MifareClassicCardType.MIFARE_CLASSIC_4K.sectorsCount) { KeyPair.DEFAULT })
    }

    fun flatten(): List<Key> = data.flatMap { listOf(it.keyA, it.keyB) }.distinct()

    operator fun get(index: Int): KeyPair = data[index]

    override fun toString(): String {
        return "KeyChain(${Arrays.toString(data)})"
    }

}
