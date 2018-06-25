package love.sola.nfc.api.mifare.classic.functions

import love.sola.nfc.api.mifare.classic.MifareClassic
import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.mifare.classic.data.Key


fun MifareClassic.isBlank(): Boolean {

    val status = Array(type.totalBlocks) { false }

    fun statusToString() = status.joinToString(separator = "") { if (it) "A" else "." }
    fun printStatus() = print("\rChecking: [${statusToString()}] ")

    for (index in 1 until type.totalBlocks) {
        if (authBlock(Key.DEFAULT, index, KeyType.A)) {
            val isDefault = if (isTrailerBlock(index)) {
                readBlock(index) == Block.DEFAULT_TRAILER
            } else {
                readBlock(index) == Block.DEFAULT
            }
            if (!isDefault) {
                println() // start a new line because we were printing status
                return false
            }
            status[index] = true
            printStatus()
        } else {
            println() // start a new line because we were printing status
            return false
        }
    }
    return true
}

private fun MifareClassic.isTrailerBlock(index: Int) = type.layout.contains(index)
