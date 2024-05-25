package love.sola.nfc.kts

import java.io.File
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.ScriptingHostConfiguration
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

@KotlinScript(
    displayName = "NFC Script",
    fileExtension = "nfc.kts",
    compilationConfiguration = NfcScriptCompilationConfiguration::class,
    evaluationConfiguration = NfcScriptEvaluationConfiguration::class,
    hostConfiguration = NfcScriptHostConfiguration::class
)
abstract class NfcScript

object NfcScriptCompilationConfiguration : ScriptCompilationConfiguration({
    ide {
        acceptedLocations(ScriptAcceptedLocation.Everywhere)
    }
    jvm {
        dependenciesFromCurrentContext(wholeClasspath = true)
    }
}) {
    private fun readResolve(): Any = NfcScriptCompilationConfiguration
}

object NfcScriptEvaluationConfiguration : ScriptEvaluationConfiguration({

}) {
    private fun readResolve(): Any = NfcScriptEvaluationConfiguration
}

object NfcScriptHostConfiguration : ScriptingHostConfiguration({
}) {
    private fun readResolve(): Any = NfcScriptHostConfiguration
}

object ScriptRunner {
    private val scriptingHost = BasicJvmScriptingHost()
    private val compileConfig = NfcScriptCompilationConfiguration
    private val evalConfig = NfcScriptEvaluationConfiguration

    fun run(script: File): ResultWithDiagnostics<EvaluationResult> {
        return scriptingHost.eval(script.toScriptSource(), compileConfig, evalConfig)
    }
}
