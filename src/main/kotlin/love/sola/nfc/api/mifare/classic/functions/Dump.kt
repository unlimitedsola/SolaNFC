package love.sola.nfc.api.mifare.classic.functions

import love.sola.nfc.api.mifare.classic.MifareClassic
import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.mifare.classic.data.Dump
import love.sola.nfc.api.mifare.classic.data.KeyChain
import love.sola.nfc.api.progress.IndexedProgressHandler
import javax.smartcardio.CardException

fun MifareClassic.dump(keyChain: KeyChain, progressHandler: IndexedProgressHandler<KeyType>? = null): Dump {
    val blocks = arrayOfNulls<Block>(type.totalBlocks)

    progressHandler?.onInit(type.totalBlocks)

    repeat(type.totalBlocks) { index ->
        val sectorIndex = type.sectorIndexOf(index)
        for (keyType in KeyType.values()) {
            if (authBlock(index, keyType, keyChain[sectorIndex][keyType])) {
                try {
                    blocks[index] = readBlock(index)
                    progressHandler?.onUpdate(index, keyType)
                    return@repeat
                } catch (e: CardException) {
                    continue
                }
            }
        }
        progressHandler?.onFinish()
        throw IllegalAccessException("Failed to auth block $index (sector: $sectorIndex).")
    }

    progressHandler?.onFinish()
    return Dump.parse(blocks.requireNoNulls())
}

fun MifareClassic.unlockedDump(progressHandler: IndexedProgressHandler<Boolean>? = null): Dump {
    if (!unlocked) throw IllegalStateException("Card is not unlocked")
    val blocks = arrayOfNulls<Block>(type.totalBlocks)

    progressHandler?.onInit(type.totalBlocks)

    repeat(type.totalBlocks) { index ->
        blocks[index] = readBlock(index)
        progressHandler?.onUpdate(index, true)
        return@repeat
    }

    progressHandler?.onFinish()
    return Dump.parse(blocks.requireNoNulls())
}
