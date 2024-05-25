package jpms

import org.gradlex.javamodule.moduleinfo.ExtraJavaModuleInfoPluginExtension

// Dependency Graph (with duplicates removed):
// +--- org.jetbrains.kotlin:kotlin-scripting-common:2.0.0
// |    \--- org.jetbrains.kotlin:kotlin-stdlib:2.0.0
// |         \--- org.jetbrains:annotations:13.0
// +--- org.jetbrains.kotlin:kotlin-reflect:2.0.0
// +--- org.jetbrains.kotlin:kotlin-scripting-jvm:2.0.0
// |    +--- org.jetbrains.kotlin:kotlin-script-runtime:2.0.0
// \--- org.jetbrains.kotlin:kotlin-scripting-jvm-host:2.0.0
// +--- org.jetbrains.kotlin:kotlin-script-runtime:2.0.0
// +--- org.jetbrains.kotlin:kotlin-compiler-embeddable:2.0.0
// |    +--- org.jetbrains.kotlin:kotlin-daemon-embeddable:2.0.0
// |    \--- org.jetbrains.intellij.deps:trove4j:1.0.20200330
// \--- org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:2.0.0
// +--- org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable:2.0.0

fun ExtraJavaModuleInfoPluginExtension.addModules() {
    // +--- org.jetbrains.kotlin:kotlin-scripting-common:2.0.0
    automaticModule("org.jetbrains.kotlin:kotlin-scripting-common", "kotlin.scripting.common")
    // |    \--- org.jetbrains.kotlin:kotlin-stdlib:2.0.0
    knownModule("org.jetbrains.kotlin:kotlin-stdlib", "kotlin.stdlib")
    // |         \--- org.jetbrains:annotations:13.0
    automaticModule("org.jetbrains:annotations", "org.jetbrains.annotations")
    // +--- org.jetbrains.kotlin:kotlin-reflect:2.0.0
    knownModule("org.jetbrains.kotlin:kotlin-reflect", "kotlin.reflect")
    // +--- org.jetbrains.kotlin:kotlin-scripting-jvm:2.0.0
    automaticModule("org.jetbrains.kotlin:kotlin-scripting-jvm", "kotlin.scripting.jvm")
    // |    +--- org.jetbrains.kotlin:kotlin-script-runtime:2.0.0
    automaticModule("org.jetbrains.kotlin:kotlin-script-runtime", "kotlin.script.runtime")
    // \--- org.jetbrains.kotlin:kotlin-scripting-jvm-host:2.0.0
    automaticModule("org.jetbrains.kotlin:kotlin-scripting-jvm-host", "kotlin.scripting.jvm.host")
    // +--- org.jetbrains.kotlin:kotlin-compiler-embeddable:2.0.0
    automaticModule("org.jetbrains.kotlin:kotlin-compiler-embeddable", "kotlin.compiler.embeddable")
    // |    +--- org.jetbrains.kotlin:kotlin-daemon-embeddable:2.0.0
    automaticModule("org.jetbrains.kotlin:kotlin-daemon-embeddable", "kotlin.daemon.embeddable")
    // |    \--- org.jetbrains.intellij.deps:trove4j:1.0.20200330
    automaticModule("org.jetbrains.intellij.deps:trove4j", "trove4j")
    // \--- org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:2.0.0
    automaticModule("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable", "kotlin.scripting.compiler.embeddable")
    // +--- org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable:2.0.0
    automaticModule(
        "org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable", "kotlin.scripting.compiler.impl.embeddable"
    )
}
