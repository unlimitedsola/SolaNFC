package love.sola.nfc.api.mifare.classic.data

import java.io.Serializable

class Sector(private val _data: Array<Block>) : Cloneable, Serializable {

    val keyA get() = Key(_data.last().data().copyOfRange(0, 6))
    val keyB get() = Key(_data.last().data().copyOfRange(10, 16))
    val accessBits get() = AccessBits(_data.last().data().copyOfRange(6, 9))

    fun data() = _data.clone()

    val size get() = _data.size
    operator fun get(index: Int): Block = _data[index]

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sector

        if (!_data.contentEquals(other._data)) return false

        return true
    }

    override fun hashCode(): Int {
        return _data.contentHashCode()
    }

    override fun toString(): String {
        return "Sector(${_data.contentToString()})"
    }
}
