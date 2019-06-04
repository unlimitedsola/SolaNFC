package love.sola.nfc.util

import org.apache.commons.codec.binary.Hex

/**
 * @author Sola
 */
fun String.hexToByteArray(): ByteArray = Hex.decodeHex(this.toCharArray())

fun ByteArray.toHexString(): String = Hex.encodeHexString(this)

fun Byte.toHexString(): String = toString(16).padStart(2, '0')
