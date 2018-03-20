package love.sola.nfc.api.mifare.classic.functions

import love.sola.nfc.api.mifare.classic.MifareClassic
import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.data.Dump
import love.sola.nfc.api.mifare.classic.data.Key

/**
 * @author Sola
 */
fun MifareClassic.format(keyA: Array<Key>, keyB: Array<Key>, dump: Dump) {
    for (sector in 0 until dump.size) {
        for (block in 0 until dump[sector].size) {
            val index = type.blockIndexOf(sector, block)
            if (authBlock(keyA[sector], index, KeyType.A)
                    && writeBlock(index, dump[sector][block])) {
                MifareClassic.log.info("Formatted block $index with key A.")
            } else if (authBlock(keyB[sector], index, KeyType.B)
                    && writeBlock(index, dump[sector][block])) {
                MifareClassic.log.info("Formatted block $index with key B.")
            } else {
                MifareClassic.log.error("Failed to format block $index!")
            }
        }
    }
}
