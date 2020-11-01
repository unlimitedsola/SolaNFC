package love.sola.nfc.cli.progress

import love.sola.nfc.api.progress.IndexedProgressHandler

/**
 * @author Sola
 */
abstract class ConsoleIndexedProgressHandler<T>(val processName: String) : IndexedProgressHandler<T> {

    private lateinit var status: Array<T?>

    override fun onInit(size: Int) {
        status = createStatusArray(size)
        printStatus()
    }

    protected abstract fun createStatusArray(size: Int): Array<T?>

    override fun onUpdate(index: Int, status: T?) {
        this.status[index] = status
        printStatus()
    }

    protected fun printStatus() {
        print("\r$processName: [${status.map(this::statusToChar).joinToString(separator = "")}] ")
    }

    protected abstract fun statusToChar(status: T?): Char

    override fun onFinish() {
        println()
    }
}