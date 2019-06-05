package love.sola.nfc.fx

import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.*
import javafx.scene.paint.Color
import love.sola.nfc.util.toHexString
import tornadofx.*

/**
 * @author Sola
 */
class MainView : View("SolaNFCFx") {

    val controller: MainController by inject()

    val leftStatus = SimpleStringProperty("Left status.")
    val rightStatus = SimpleStringProperty("Right status.")

    init {
        controller.init()
    }

    override val root = vbox {
        menubar {
            menu("File") {
                item("Open")
            }
            menu("Edit") { }
            menu("Help") { }
        }
        borderpane {
            padding = insets(5)
            vgrow = Priority.ALWAYS
            prefHeight = 480.0
            prefWidth = 640.0
            left = vbox {
                hbox {
                    label("Type:")
                    label {
                        textProperty().bind(cardProperty.stringBinding { it?.type?.name })
                    }
                }
                hbox {
                    label("UID:")
                    label {
                        textProperty().bind(cardProperty.stringBinding {
                            it?.getUID()?.toHexString()
                        })
                    }
                }
            }
        }
        hbox {
            padding = insets(horizontal = 5)
            border = Border(
                BorderStroke(
                    Color.LIGHTGRAY,
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    BorderWidths.DEFAULT
                )
            )
            label(leftStatus) { textFill = Color.GRAY }
            pane { hgrow = Priority.ALWAYS }
            label(rightStatus) { textFill = Color.GRAY }
        }
    }

}
