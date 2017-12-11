package love.sola.nfc.fx

import javafx.beans.property.SimpleObjectProperty
import love.sola.nfc.api.mifare.classic.MifareClassic
import javax.smartcardio.CardTerminal

/**
 * @author Sola
 */
val terminalProperty = SimpleObjectProperty<CardTerminal>()
val cardProperty = SimpleObjectProperty<MifareClassic>()
