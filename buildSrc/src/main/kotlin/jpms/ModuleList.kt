package jpms

import org.gradlex.javamodule.moduleinfo.ExtraJavaModuleInfoPluginExtension

fun ExtraJavaModuleInfoPluginExtension.addModules() {
    // stdlib have well-defined explicit descriptors.
    knownModule("org.jetbrains.kotlin:kotlin-stdlib", "kotlin.stdlib")
    // stdlib common doesn't have any descriptors nor dependencies.
    automaticModule("org.jetbrains.kotlin:kotlin-stdlib-common", "kotlin.stdlib.common")
    // jetbrains annotations doesn't have any descriptors.
    automaticModule("org.jetbrains:annotations", "org.jetbrains.annotations")

    defaultModule("org.jetbrains.kotlin:kotlin-scripting-common", "kotlin.scripting.common")
    // script runtime doesn't have any descriptors nor dependencies.
    automaticModule("org.jetbrains.kotlin:kotlin-script-runtime", "kotlin.script.runtime")

    defaultModule("org.jetbrains.kotlin:kotlin-scripting-jvm", "kotlin.scripting.jvm")
    // scripting jvm host doesn't have any dependencies.
    automaticModule("org.jetbrains.kotlin:kotlin-scripting-jvm-host", "kotlin.scripting.jvm.host")

    defaultModule("org.jetbrains.kotlin:kotlin-scripting-dependencies", "kotlin.scripting.dependencies")
    defaultModule("org.jetbrains.kotlin:kotlin-scripting-dependencies-maven", "kotlin.scripting.dependencies.maven")

    automaticModule("org.jetbrains.kotlin:kotlin-daemon-embeddable", "kotlin.daemon.embeddable")
    defaultModule("org.jetbrains.kotlin:kotlin-compiler-embeddable", "kotlin.compiler.embeddable")
    defaultModule("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable", "kotlin.scripting.compiler.embeddable")
    defaultModule(
        "org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable", "kotlin.scripting.compiler.impl.embeddable"
    )

    automaticModule("org.jetbrains.intellij.deps:trove4j", "trove4j")

    knownModule("commons-io:commons-io", "commons.io")
    // maven resolvers have automatic modules.
    knownModule("org.apache.maven.resolver:maven-resolver-connector-basic", "org.apache.maven.resolver.connector.basic")
    knownModule("org.apache.maven.resolver:maven-resolver-impl", "org.apache.maven.resolver.impl")
    knownModule("org.apache.maven.resolver:maven-resolver-transport-file", "org.apache.maven.resolver.transport.file")
    knownModule("org.apache.maven.resolver:maven-resolver-transport-wagon", "org.apache.maven.resolver.transport.wagon")

    // wagon does not have any descriptors.
    automaticModule("org.apache.maven.wagon:wagon-http", "org.apache.maven.wagon.providers.http")
    automaticModule("org.apache.maven.wagon:wagon-http-shared", "wagon.http.shared")
    automaticModule("org.apache.maven.wagon:wagon-provider-api", "wagon.provider.api")

    // maven modules do not have any descriptors.
    automaticModule("org.apache.maven:maven-core", "org.apache.maven.core")
    automaticModule("org.apache.maven:maven-resolver-provider", "maven.resolver.provider")
    automaticModule("org.apache.maven:maven-plugin-api", "maven.plugin.api")
    automaticModule("org.apache.maven:maven-model-builder", "maven.model.builder")
    automaticModule("org.apache.maven:maven-model", "maven.model")
    automaticModule("org.apache.maven:maven-settings-builder", "maven.settings.builder")
    automaticModule("org.apache.maven:maven-settings", "maven.settings")
    automaticModule("org.apache.maven:maven-builder-support", "maven.builder.support")
    automaticModule("org.apache.maven:maven-repository-metadata", "maven.repository.metadata")
    automaticModule("org.apache.maven:maven-artifact", "maven.artifact")
    automaticModule("org.apache.maven.shared:maven-shared-utils", "maven.shared.utils")

    // generated
    automaticModule("org.eclipse.sisu:org.eclipse.sisu.plexus", "org.eclipse.sisu.plexus")
    automaticModule("org.eclipse.sisu:org.eclipse.sisu.inject", "org.eclipse.sisu.inject")
    automaticModule("org.codehaus.plexus:plexus-sec-dispatcher", "plexus.sec.dispatcher")
    automaticModule("org.codehaus.plexus:plexus-cipher", "plexus.cipher")
    automaticModule("javax.inject:javax.inject", "javax.inject")
    automaticModule("org.codehaus.plexus:plexus-utils", "plexus.utils")
    automaticModule("org.codehaus.plexus:plexus-classworlds", "plexus.classworlds")
    automaticModule("org.codehaus.plexus:plexus-interpolation", "plexus.interpolation")
    automaticModule("org.codehaus.plexus:plexus-component-annotations", "plexus.component.annotations")
    automaticModule("javax.annotation:javax.annotation-api", "javax.annotation.api")
    automaticModule("aopalliance:aopalliance", "aopalliance")
    automaticModule("com.google.code.findbugs:jsr305", "jsr305")
    automaticModule("org.checkerframework:checker-compat-qual", "checker.compat.qual")
    automaticModule("com.google.errorprone:error_prone_annotations", "error_prone_annotations")
    automaticModule("com.google.j2objc:j2objc-annotations", "j2objc.annotations")
    automaticModule("org.codehaus.mojo:animal-sniffer-annotations", "animal.sniffer.annotations")
}
