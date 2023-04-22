package love.sola.nfc.api.mifare.classic.functions

import love.sola.nfc.api.mifare.classic.MifareClassic
import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.data.AccessBits
import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.mifare.classic.data.Key
import love.sola.nfc.api.progress.IndexedProgressHandler

private val WEIRD_DEFAULT_TRAILER =
    Block(Key("000000000000").data() + AccessBits.DEFAULT.data() + Key.DEFAULT.data())

fun MifareClassic.isBlank(progressHandler: IndexedProgressHandler<Boolean>? = null): Boolean {

    progressHandler?.onInit(type.totalBlocks)

    for (index in 1 until type.totalBlocks) {
        if (authBlock(index, KeyType.A, Key.DEFAULT)) {
            val isDefault = if (isTrailerBlock(index)) {
                readBlock(index) == Block.DEFAULT_TRAILER
                        || readBlock(index) == WEIRD_DEFAULT_TRAILER
            } else {
                readBlock(index) == Block.DEFAULT
            }
            progressHandler?.onUpdate(index, isDefault)
            if (!isDefault) {
                progressHandler?.onFinish()
                return false
            }
        } else {
            progressHandler?.onUpdate(index, false)
            progressHandler?.onFinish()
            return false
        }
    }

    progressHandler?.onFinish()
    return true
}

private fun MifareClassic.isTrailerBlock(index: Int) = type.layout.contains(index)
