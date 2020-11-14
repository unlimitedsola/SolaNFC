package love.sola.nfc.cli.progress

class BooleanConsoleProgressHandler(processName: String) : ConsoleIndexedProgressHandler<Boolean>(processName) {
    override fun createStatusArray(size: Int): Array<Boolean?> = Array(size) { null }
    override fun statusToChar(status: Boolean?): Char = when (status) {
        true -> '='
        false -> 'x'
        null -> '.'
    }
}