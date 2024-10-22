package love.sola.nfc

import love.sola.nfc.kts.ScriptRunner
import java.io.File
import kotlin.script.experimental.api.valueOrThrow

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("usage: <app> <script file>")
        return
    } else {
        val scriptFile = args[0]
        ScriptRunner.run(File(scriptFile)).valueOrThrow()
    }
}
