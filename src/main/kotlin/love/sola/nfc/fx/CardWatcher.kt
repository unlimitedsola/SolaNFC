package love.sola.nfc.fx

import javafx.application.Platform
import love.sola.nfc.api.mifare.classic.MifareClassic
import java.util.logging.Level
import java.util.logging.Logger
import javax.smartcardio.CardTerminal

/**
 * @author Sola
 */
class CardWatcher(val terminal: CardTerminal) : Thread("Card Watcher") {

    companion object {
        val log = Logger.getLogger(CardWatcher::javaClass.name)
    }

    init {
        isDaemon = true
    }

    override fun run() {
        while (true) {
            try {
                if (cardProperty.value == null) {
                    if (terminal.waitForCardPresent(1000)) {
                        val card = terminal.connect("*")
                        Platform.runLater {
                            cardProperty.value = MifareClassic(card)
                        }
                    }
                } else {
                    if (terminal.waitForCardAbsent(1000)) {
                        Platform.runLater {
                            cardProperty.value = null
                        }
                    }
                }
            } catch(e: Exception) {
                log.log(Level.SEVERE, "Exception in CardWatcher", e)
            }
        }
    }

}