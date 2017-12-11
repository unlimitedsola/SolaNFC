package love.sola.nfc.fx

import javafx.util.StringConverter
import javax.smartcardio.CardTerminal

/**
 * @author Sola
 */
object CardTerminalStringConverter : StringConverter<CardTerminal?>() {
    override fun fromString(string: String?): CardTerminal? = null
    override fun toString(`object`: CardTerminal?): String? = `object`?.name
}