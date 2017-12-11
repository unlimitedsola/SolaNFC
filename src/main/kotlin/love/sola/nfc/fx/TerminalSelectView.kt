package love.sola.nfc.fx

import javafx.beans.property.SimpleListProperty
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.layout.Priority
import tornadofx.*
import java.lang.Exception
import java.util.logging.Level
import javax.smartcardio.CardTerminal
import javax.smartcardio.TerminalFactory

/**
 * @author Sola
 */
class TerminalSelectView : View("Select Card Terminal") {

    val terminals = SimpleListProperty<CardTerminal>(getTerminals().observable())

    override val root = hbox(spacing = 10) {
        padding = tornadofx.insets(all = 5)
        alignment = Pos.CENTER_LEFT
        isFillHeight = true
        label("Card Reader:")
        combobox(terminalProperty, terminals) {
            converter = CardTerminalStringConverter
        }
        button("Refresh") {
            action {
                terminals.set(getTerminals().observable())
            }
        }
        pane { hgrow = Priority.ALWAYS }
        button("OK") {
            isDefaultButton = true
            action {
                replaceWith(find<MainView>(), null, true, true)
            }
        }
    }

    private fun getTerminals(): List<CardTerminal> {
        try {
            val factory = TerminalFactory.getInstance("PC/SC", null)
            log.info("Terminal Factory: $factory")
            return factory.terminals().list()
        } catch (e: Exception) {
            log.log(Level.SEVERE, "Get card terminals failed.", e)
            alert(Alert.AlertType.ERROR, "No card terminal detected.", e.message ?: "", ButtonType.OK)
            return emptyList()
        }
    }

}