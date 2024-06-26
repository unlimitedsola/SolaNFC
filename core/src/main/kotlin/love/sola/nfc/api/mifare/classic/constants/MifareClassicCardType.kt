package love.sola.nfc.api.mifare.classic.constants

import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.mifare.classic.data.Dump
import javax.smartcardio.ATR

/**
 * Only ISO 14443A, Part3 standard is supported (0x03)
 */
enum class MifareClassicCardType(val size: Int, val layout: IntArray) {

    MIFARE_CLASSIC_1K(1024, (3..63 step 4).toList().toIntArray()),
    MIFARE_CLASSIC_4K(
        4096,
        ((3..127 step 4) union (143..255 step 16)).toList().toIntArray()
    );

    val totalSectors get() = layout.size
    val totalBlocks get() = layout.last() + 1

    fun sectorIndexOf(blockIndex: Int): Int {
        for ((index, value) in layout.withIndex()) {
            if (blockIndex <= value) {
                return index
            }
        }
        throw IndexOutOfBoundsException("Index of block $blockIndex is out of bounds.")
    }

    fun blockIndexOf(sectorIndex: Int, blockIndexOfSector: Int): Int {
        val sectorStart = if (sectorIndex <= 0) 0 else layout[sectorIndex - 1] + 1
        return sectorStart + blockIndexOfSector
    }

    fun defaultDump() = Array(totalBlocks) { Block.DEFAULT }.apply {
        layout.forEach { index ->
            set(index, Block.DEFAULT_TRAILER)
        }
    }.let { Dump.parse(it) }

    companion object {

        private const val ISO_14443A_STANDARD: Byte = 0x03

        fun fromATR(atr: ATR): MifareClassicCardType {
            if (atr.bytes[12] != ISO_14443A_STANDARD)
                throw UnsupportedOperationException("Unsupported standard. only ISO 14443A Part3 standard supported.")
            val c0 = atr.bytes[13].toUByte().toInt()
            val c1 = atr.bytes[14].toUByte().toInt()
            return when {
                c0 == 0x00 && c1 == 0x01 -> MIFARE_CLASSIC_1K
                // Magic card
                c0 == 0x00 && c1 == 0x03 -> MIFARE_CLASSIC_1K
                // Infineon
                c0 == 0xFF && c1 == 0x88 -> MIFARE_CLASSIC_1K
                c0 == 0x00 && c1 == 0x02 -> MIFARE_CLASSIC_4K
                else -> throw UnsupportedOperationException("Unknown card type for C0=${atr.bytes[13]} C1=${atr.bytes[14]}")
            }
        }
    }
}
