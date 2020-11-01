package love.sola.nfc.api.mifare.classic.functions

import love.sola.nfc.api.mifare.classic.MifareClassic
import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.data.Dump
import love.sola.nfc.api.mifare.classic.data.KeyChain
import love.sola.nfc.api.progress.IndexedProgressHandler

/**
 * @author Sola
 */
fun MifareClassic.format(keyChain: KeyChain, dump: Dump, progressHandler: IndexedProgressHandler<KeyType>? = null) {
    if (dump.size != type.totalSectors) {
        throw IllegalArgumentException("dump's size doesn't match the current card.")
    }

    progressHandler?.onInit(type.totalBlocks)

    for (sector in 0 until dump.size) {
        loop@ for (block in 0 until dump[sector].size) {
            val index = type.blockIndexOf(sector, block)
            if (index == 0) continue
            for (keyType in KeyType.values()) {
                if (authBlock(index, keyType, keyChain[sector][keyType]) &&
                    writeBlock(index, dump[sector][block])
                ) {
                    progressHandler?.onUpdate(index, keyType)
                    continue@loop
                }
            }
            progressHandler?.onFinish()
            throw IllegalAccessException("Failed to auth block $index (sector: $sector).")
        }
    }

    progressHandler?.onFinish()
}

/**
 * NOTE: this method will also write the UID Block (block 0)
 */
fun MifareClassic.unlockedFormat(dump: Dump, progressHandler: IndexedProgressHandler<Boolean>? = null) {
    if (!unlocked) throw IllegalStateException("Card is not unlocked")
    if (dump.size != type.totalSectors) {
        throw IllegalArgumentException("dump's size doesn't match the current card.")
    }

    progressHandler?.onInit(type.totalBlocks)

    for (sector in 0 until dump.size) {
        loop@ for (block in 0 until dump[sector].size) {
            val index = type.blockIndexOf(sector, block)
            writeBlock(index, dump[sector][block])
            progressHandler?.onUpdate(index, true)
            continue@loop
        }
    }

    progressHandler?.onFinish()
}
