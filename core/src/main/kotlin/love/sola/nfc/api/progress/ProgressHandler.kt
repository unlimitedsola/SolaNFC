package love.sola.nfc.api.progress

interface IndexedProgressHandler<in T> {
    fun onInit(size: Int)
    fun onUpdate(index: Int, status: T?)
    fun onFinish()
}

