package love.sola.nfc

import love.sola.nfc.kts.Dojo
import java.io.File

fun main(args: Array<String>) {
    val scriptName = args[0]
    File("scripts/$scriptName.kts").readText().let {
        Dojo.script(it)
    }
}