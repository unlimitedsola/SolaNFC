package love.sola.nfc.api.progress

/**
 * @author Sola
 */
interface IndexedProgressHandler<in T> {
    fun onInit(size: Int)
    fun onUpdate(index: Int, status: T?)
    fun onFinish()
}

