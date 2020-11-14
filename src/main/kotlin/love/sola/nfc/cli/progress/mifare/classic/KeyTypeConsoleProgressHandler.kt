package love.sola.nfc.cli.progress.mifare.classic

import love.sola.nfc.api.mifare.classic.constants.KeyType
import love.sola.nfc.cli.progress.ConsoleIndexedProgressHandler

class KeyTypeConsoleProgressHandler(processName: String) : ConsoleIndexedProgressHandler<KeyType>(processName) {
    override fun createStatusArray(size: Int): Array<KeyType?> = Array(size) { null }
    override fun statusToChar(status: KeyType?): Char = when (status) {
        KeyType.A -> 'A'
        KeyType.B -> 'B'
        null -> '.'
    }
}