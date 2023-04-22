package love.sola.nfc.cli.progress

class GenericConsoleProgressHandler(processName: String) : ConsoleIndexedProgressHandler<Any>(processName) {
    override fun createStatusArray(size: Int): Array<Any?> = Array(size) { null }
    override fun statusToChar(status: Any?): Char = if (status == null) '=' else '.'
}