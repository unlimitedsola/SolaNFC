package love.sola.nfc.api

import love.sola.nfc.api.mifare.classic.MifareClassic
import javax.smartcardio.TerminalFactory

/**
 * @author Sola
 */
private val terminal by lazy {
    TerminalFactory.getDefault().terminals().list()[0]
}

fun getCard(): MifareClassic? = terminal.waitForCardPresent(0)
        .then { terminal.connect("*") }?.let { MifareClassic(it) }

fun waitDisconnect() = terminal.waitForCardAbsent(0)

private inline fun <R> Boolean.then(block: () -> R): R? = if (this) block() else null

