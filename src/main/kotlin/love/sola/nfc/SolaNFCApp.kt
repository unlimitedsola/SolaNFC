package love.sola.nfc

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import love.sola.nfc.fx.Styles
import love.sola.nfc.fx.TerminalSelectView
import tornadofx.App
import tornadofx.FX

/**
 * @author Sola
 */
class SolaNFCApp : App(TerminalSelectView::class, Styles::class) {

    init {
        FX.layoutDebuggerShortcut = KeyCodeCombination(KeyCode.J, KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.ALT_DOWN)
    }

}