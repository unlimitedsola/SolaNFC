package love.sola.nfc.util

import java.util.*

fun String.hexToByteArray(): ByteArray = HexFormat.of().parseHex(this)

fun ByteArray.toHexString(): String = HexFormat.of().formatHex(this)

fun Byte.toHexString(): String = toString(16).padStart(2, '0')
