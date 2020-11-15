package love.sola.nfc.kts

import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object Dojo {

    init {
        setIdeaIoUseFallback()
    }

    private val engine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")

    fun script(script: String): Any? = engine.eval(script)
}