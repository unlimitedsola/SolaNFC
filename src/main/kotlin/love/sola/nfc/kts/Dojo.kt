package love.sola.nfc.kts

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

/**
 * @author Sola
 */
object Dojo {

    val engine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")

    fun script(script: String): Any? = engine.eval(script)

}