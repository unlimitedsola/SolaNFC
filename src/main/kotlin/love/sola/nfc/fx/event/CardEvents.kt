package love.sola.nfc.fx.event

import love.sola.nfc.api.mifare.classic.MifareClassic
import tornadofx.FXEvent

/**
 * @author Sola
 */
class CardPresentEvent(val card: MifareClassic) : FXEvent()

object CardAbsentEvent : FXEvent()
