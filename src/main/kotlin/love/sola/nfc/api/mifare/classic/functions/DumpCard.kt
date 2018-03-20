package love.sola.nfc.api.mifare.classic.functions

import love.sola.nfc.api.mifare.classic.MifareClassic
import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.mifare.classic.data.Dump
import love.sola.nfc.api.mifare.classic.data.Key

/**
 * @author Sola
 */
fun MifareClassic.dump(keyA: Array<Key>, keyB: Array<Key>): Dump {
    val blocks = arrayOfNulls<Block>(type.blocksCount)
    repeat(type.blocksCount) { index ->
        val sectorIndex = type.sectorIndexOf(index)
        if (authBlock(keyA[sectorIndex], index, KeyType.A)) {
            blocks[index] = readBlock(index)
            MifareClassic.log.info("Dumped block $index with key A.")
        } else if (authBlock(keyB[sectorIndex], index, KeyType.B)) {
            blocks[index] = readBlock(index)
            MifareClassic.log.info("Dumped block $index with key B.")
        } else {
            MifareClassic.log.error("Failed to dump block $index!")
        }
    }
    return Dump.parse(blocks.requireNoNulls())
}
