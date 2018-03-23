package love.sola.nfc.api.mifare.classic.functions

import love.sola.nfc.api.mifare.classic.MifareClassic
import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.mifare.classic.data.Dump
import love.sola.nfc.api.mifare.classic.data.KeyChain
import javax.smartcardio.CardException

/**
 * @author Sola
 */
fun MifareClassic.dump(keyChain: KeyChain): Dump {
    val blocks = arrayOfNulls<Block>(type.totalBlocks)

    val status = Array<KeyType?>(type.totalBlocks) { null }

    fun statusToString() = status.map { it?.name ?: '.' }.joinToString(separator = "")
    fun printStatus() = print("\rDumping: [${statusToString()}] ")

    repeat(type.totalBlocks) { index ->
        val sectorIndex = type.sectorIndexOf(index)
        for (keyType in KeyType.values()) {
            if (authBlock(keyChain[sectorIndex][keyType], index, keyType)) {
                try {
                    blocks[index] = readBlock(index)
                    status[index] = keyType
                    printStatus()
                    return@repeat
                } catch (e: CardException) {
                    continue
                }
            }
        }
        println() // start a new line because we were printing status
        throw IllegalAccessException("Failed to auth block $index (sector: $sectorIndex).")
    }
    return Dump.parse(blocks.requireNoNulls())
}
