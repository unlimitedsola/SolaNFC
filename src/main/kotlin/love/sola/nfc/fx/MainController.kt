package love.sola.nfc.fx

import tornadofx.Controller

/**
 * @author Sola
 */
class MainController : Controller() {

    val view: MainView by inject()

    val cardWatcher = CardWatcher(terminalProperty.value)

    fun init() {
        cardWatcher.start()
    }

}