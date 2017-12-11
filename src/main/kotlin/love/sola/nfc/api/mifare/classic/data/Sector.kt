package love.sola.nfc.api.mifare.classic.data

import java.io.Serializable
import java.util.*

/**
 * @author Sola
 */
class Sector(private val blocks: Array<Block>) : Cloneable, Serializable {

    val keyA get () = Key(blocks.last().data.copyOfRange(0, 6))
    val keyB get () = Key(blocks.last().data.copyOfRange(10, 16))
    val accessBits get () = AccessBits(blocks.last().data.copyOfRange(6, 9))

    val size get() = blocks.size
    operator fun get(index: Int): Block = blocks[index]

    override fun toString(): String {
        return "Sector(${Arrays.toString(blocks)})"
    }

}