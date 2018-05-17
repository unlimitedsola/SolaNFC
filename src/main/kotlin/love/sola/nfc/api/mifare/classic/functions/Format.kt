package love.sola.nfc.api.mifare.classic.functions

import love.sola.nfc.api.mifare.classic.MifareClassic
import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.data.Dump
import love.sola.nfc.api.mifare.classic.data.KeyChain

/**
 * @author Sola
 */
fun MifareClassic.format(keyChain: KeyChain, dump: Dump) {
    if (dump.size != type.totalSectors) {
        throw IllegalArgumentException("dump's size doesn't match the current card.")
    }
    val status = Array<KeyType?>(type.totalBlocks) { null }

    fun statusToString() = status.map { it?.name ?: '.' }.joinToString(separator = "")
    fun printStatus() = print("\rFormatting: [${statusToString()}] ")

    for (sector in 0 until dump.size) {
        loop@ for (block in 0 until dump[sector].size) {
            val index = type.blockIndexOf(sector, block)
            if (index == 0) continue
            for (keyType in KeyType.values()) {
                if (authBlock(keyChain[sector][keyType], index, keyType) &&
                    writeBlock(index, dump[sector][block])
                ) {
                    status[index] = keyType
                    printStatus()
                    continue@loop
                }
            }
            println() // start a new line because we were printing status
            throw IllegalAccessException("Failed to auth block $index (sector: $sector).")
        }
    }
    println()
}
