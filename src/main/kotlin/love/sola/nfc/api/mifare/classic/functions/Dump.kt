package love.sola.nfc.api.mifare.classic.functions

import love.sola.nfc.api.mifare.classic.MifareClassic
import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.mifare.classic.data.Dump
import love.sola.nfc.api.mifare.classic.data.Key
import love.sola.nfc.api.mifare.classic.data.KeyChain

/**
 * @author Sola
 */
fun MifareClassic.dump(keyChain: KeyChain): Dump {
    val blocks = arrayOfNulls<Block>(type.blocksCount)
    repeat(type.blocksCount) { index ->
        val sectorIndex = type.sectorIndexOf(index)
        if (authBlock(keyChain[sectorIndex].keyA, index, KeyType.A)) {
            blocks[index] = readBlock(index)
            MifareClassic.log.info("Dumped block $index with key A.")
        } else if (authBlock(keyChain[sectorIndex].keyB, index, KeyType.B)) {
            blocks[index] = readBlock(index)
            MifareClassic.log.info("Dumped block $index with key B.")
        } else {
            MifareClassic.log.error("Failed to dump block $index!")
        }
    }
    return Dump.parse(blocks.requireNoNulls())
}
