package jpms

import org.gradlex.javamodule.moduleinfo.ExtraJavaModuleInfoPluginExtension

/* Dependency Graph
+--- org.jetbrains.kotlin:kotlin-scripting-common:1.8.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21
|         +--- org.jetbrains.kotlin:kotlin-stdlib-common:1.8.21
|         \--- org.jetbrains:annotations:13.0
+--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21
|    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
|    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.21
|         \--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
+--- org.jetbrains.kotlin:kotlin-reflect:1.8.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
+--- org.jetbrains.kotlin:kotlin-scripting-jvm:1.8.21
|    +--- org.jetbrains.kotlin:kotlin-script-runtime:1.8.21
|    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
|    \--- org.jetbrains.kotlin:kotlin-scripting-common:1.8.21 (*)
\--- org.jetbrains.kotlin:kotlin-scripting-jvm-host:1.8.21
     +--- org.jetbrains.kotlin:kotlin-script-runtime:1.8.21
     +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
     +--- org.jetbrains.kotlin:kotlin-scripting-common:1.8.21 (*)
     +--- org.jetbrains.kotlin:kotlin-scripting-jvm:1.8.21 (*)
     +--- org.jetbrains.kotlin:kotlin-compiler-embeddable:1.8.21
     |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
     |    +--- org.jetbrains.kotlin:kotlin-script-runtime:1.8.21
     |    +--- org.jetbrains.kotlin:kotlin-reflect:1.6.10 -> 1.8.21 (*)
     |    +--- org.jetbrains.kotlin:kotlin-daemon-embeddable:1.8.21
     |    +--- org.jetbrains.intellij.deps:trove4j:1.0.20200330
     |    \--- net.java.dev.jna:jna:5.6.0
     \--- org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:1.8.21
          +--- org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable:1.8.21
          |    +--- org.jetbrains.kotlin:kotlin-scripting-common:1.8.21 (*)
          |    +--- org.jetbrains.kotlin:kotlin-scripting-jvm:1.8.21 (*)
          |    \--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
          \--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
 */

fun ExtraJavaModuleInfoPluginExtension.addModules() {
    /*
+--- org.jetbrains.kotlin:kotlin-scripting-common:1.8.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21
|         +--- org.jetbrains.kotlin:kotlin-stdlib-common:1.8.21
|         \--- org.jetbrains:annotations:13.0
     */
    defineModule("org.jetbrains.kotlin:kotlin-scripting-common", "kotlin.scripting.common")
    // stdlib have well-defined explicit descriptors.
    knownModule("org.jetbrains.kotlin:kotlin-stdlib", "kotlin.stdlib")
    // stdlib common doesn't have any descriptors nor dependencies.
    defineModule("org.jetbrains.kotlin:kotlin-stdlib-common", "kotlin.stdlib.common")
    // jetbrains annotations doesn't have any descriptors.
    defineModule("org.jetbrains:annotations", "org.jetbrains.annotations")
    /*
+--- org.jetbrains.kotlin:kotlin-reflect:1.8.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
     */
    knownModule("org.jetbrains.kotlin:kotlin-reflect", "kotlin.reflect")
    /*
+--- org.jetbrains.kotlin:kotlin-scripting-jvm:1.8.21
|    +--- org.jetbrains.kotlin:kotlin-script-runtime:1.8.21
|    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
|    \--- org.jetbrains.kotlin:kotlin-scripting-common:1.8.21 (*)
     */
    defineModule("org.jetbrains.kotlin:kotlin-scripting-jvm", "kotlin.scripting.jvm")
    // script runtime doesn't have any descriptors nor dependencies.
    defineModule("org.jetbrains.kotlin:kotlin-script-runtime", "kotlin.script.runtime")
    /*
\--- org.jetbrains.kotlin:kotlin-scripting-jvm-host:1.8.21
     +--- org.jetbrains.kotlin:kotlin-script-runtime:1.8.21
     +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
     +--- org.jetbrains.kotlin:kotlin-scripting-common:1.8.21 (*)
     +--- org.jetbrains.kotlin:kotlin-scripting-jvm:1.8.21 (*)
     +--- org.jetbrains.kotlin:kotlin-compiler-embeddable:1.8.21
     |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
     |    +--- org.jetbrains.kotlin:kotlin-script-runtime:1.8.21
     |    +--- org.jetbrains.kotlin:kotlin-reflect:1.6.10 -> 1.8.21 (*)
     |    +--- org.jetbrains.kotlin:kotlin-daemon-embeddable:1.8.21
     |    +--- org.jetbrains.intellij.deps:trove4j:1.0.20200330
     |    \--- net.java.dev.jna:jna:5.6.0
     \--- org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:1.8.21
          +--- org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable:1.8.21
          |    +--- org.jetbrains.kotlin:kotlin-scripting-common:1.8.21 (*)
          |    +--- org.jetbrains.kotlin:kotlin-scripting-jvm:1.8.21 (*)
          |    \--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
          \--- org.jetbrains.kotlin:kotlin-stdlib:1.8.21 (*)
     */
    // scripting jvm host doesn't have any dependencies.
    defineModule("org.jetbrains.kotlin:kotlin-scripting-jvm-host", "kotlin.scripting.jvm.host")

    defineModule("org.jetbrains.kotlin:kotlin-compiler-embeddable", "kotlin.compiler.embeddable")
    defineModule("org.jetbrains.kotlin:kotlin-daemon-embeddable", "kotlin.daemon.embeddable")
    defineModule("org.jetbrains.intellij.deps:trove4j", "trove4j")
    defineModule("net.java.dev.jna:jna", "com.sun.jna")

    defineModule("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable", "kotlin.scripting.compiler.embeddable")
    defineModule(
        "org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable", "kotlin.scripting.compiler.impl.embeddable"
    )
}
