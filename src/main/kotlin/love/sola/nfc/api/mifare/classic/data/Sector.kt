package love.sola.nfc.api.mifare.classic.data

import java.io.Serializable
import java.util.*

/**
 * @author Sola
 */
class Sector(private val _data: Array<Block>) : Cloneable, Serializable {

    val keyA get () = Key(_data.last().data().copyOfRange(0, 6))
    val keyB get () = Key(_data.last().data().copyOfRange(10, 16))
    val accessBits get () = AccessBits(_data.last().data().copyOfRange(6, 9))

    fun data() = _data.clone()

    val size get() = _data.size
    operator fun get(index: Int): Block = _data[index]

    override fun toString(): String {
        return "Sector(${Arrays.toString(_data)})"
    }

}
