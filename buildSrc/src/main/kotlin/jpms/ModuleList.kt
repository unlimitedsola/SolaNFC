package jpms

import org.gradlex.javamodule.moduleinfo.ExtraJavaModuleInfoPluginExtension

fun ExtraJavaModuleInfoPluginExtension.addModules() {
    // stdlib have well-defined explicit descriptors.
    knownModule("org.jetbrains.kotlin:kotlin-stdlib", "kotlin.stdlib")
    // stdlib common doesn't have any descriptors nor dependencies.
    defineModule("org.jetbrains.kotlin:kotlin-stdlib-common", "kotlin.stdlib.common")
    // jetbrains annotations doesn't have any descriptors.
    defineModule("org.jetbrains:annotations", "org.jetbrains.annotations")

    defineModule("org.jetbrains.kotlin:kotlin-scripting-common", "kotlin.scripting.common")
    // script runtime doesn't have any descriptors nor dependencies.
    defineModule("org.jetbrains.kotlin:kotlin-script-runtime", "kotlin.script.runtime")

    defineModule("org.jetbrains.kotlin:kotlin-scripting-jvm", "kotlin.scripting.jvm")
    // scripting jvm host doesn't have any dependencies.
    defineModule("org.jetbrains.kotlin:kotlin-scripting-jvm-host", "kotlin.scripting.jvm.host")

    defineModule("org.jetbrains.kotlin:kotlin-scripting-dependencies", "kotlin.scripting.dependencies")
    defineModule("org.jetbrains.kotlin:kotlin-scripting-dependencies-maven", "kotlin.scripting.dependencies.maven")

    defineModule("org.jetbrains.kotlin:kotlin-daemon-embeddable", "kotlin.daemon.embeddable")
    defineModule("org.jetbrains.kotlin:kotlin-compiler-embeddable", "kotlin.compiler.embeddable")
    defineModule("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable", "kotlin.scripting.compiler.embeddable")
    defineModule(
        "org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable", "kotlin.scripting.compiler.impl.embeddable"
    )

    defineModule("org.jetbrains.intellij.deps:trove4j", "trove4j")

    knownModule("commons-io:commons-io", "commons.io")
    // maven resolvers have automatic modules.
    knownModule("org.apache.maven.resolver:maven-resolver-connector-basic", "org.apache.maven.resolver.connector.basic")
    knownModule("org.apache.maven.resolver:maven-resolver-impl", "org.apache.maven.resolver.impl")
    knownModule("org.apache.maven.resolver:maven-resolver-transport-file", "org.apache.maven.resolver.transport.file")
    knownModule("org.apache.maven.resolver:maven-resolver-transport-wagon", "org.apache.maven.resolver.transport.wagon")

    // wagon does not have any descriptors.
    defineModule("org.apache.maven.wagon:wagon-http", "org.apache.maven.wagon.providers.http")
    defineModule("org.apache.maven.wagon:wagon-http-shared", "wagon.http.shared")
    defineModule("org.apache.maven.wagon:wagon-provider-api", "wagon.provider.api")

    // maven modules do not have any descriptors.
    defineModule("org.apache.maven:maven-core", "org.apache.maven.core")
    defineModule("org.apache.maven:maven-resolver-provider", "maven.resolver.provider")
    defineModule("org.apache.maven:maven-plugin-api", "maven.plugin.api")
    defineModule("org.apache.maven:maven-model-builder", "maven.model.builder")
    defineModule("org.apache.maven:maven-model", "maven.model")
    defineModule("org.apache.maven:maven-settings-builder", "maven.settings.builder")
    defineModule("org.apache.maven:maven-settings", "maven.settings")
    defineModule("org.apache.maven:maven-builder-support", "maven.builder.support")
    defineModule("org.apache.maven:maven-repository-metadata", "maven.repository.metadata")
    defineModule("org.apache.maven:maven-artifact", "maven.artifact")
    defineModule("org.apache.maven.shared:maven-shared-utils", "maven.shared.utils")

    // generated
    defineModule("org.eclipse.sisu:org.eclipse.sisu.plexus", "org.eclipse.sisu.plexus")
    defineModule("org.eclipse.sisu:org.eclipse.sisu.inject", "org.eclipse.sisu.inject")
    defineModule("org.codehaus.plexus:plexus-sec-dispatcher", "plexus.sec.dispatcher")
    defineModule("org.codehaus.plexus:plexus-cipher", "plexus.cipher")
    defineModule("javax.inject:javax.inject", "javax.inject")
    defineModule("org.codehaus.plexus:plexus-utils", "plexus.utils")
    defineModule("org.codehaus.plexus:plexus-classworlds", "plexus.classworlds")
    defineModule("org.codehaus.plexus:plexus-interpolation", "plexus.interpolation")
    defineModule("org.codehaus.plexus:plexus-component-annotations", "plexus.component.annotations")
    defineModule("javax.annotation:javax.annotation-api", "javax.annotation.api")
    defineModule("aopalliance:aopalliance", "aopalliance")
    defineModule("com.google.code.findbugs:jsr305", "jsr305")
    defineModule("org.checkerframework:checker-compat-qual", "checker.compat.qual")
    defineModule("com.google.errorprone:error_prone_annotations", "error_prone_annotations")
    defineModule("com.google.j2objc:j2objc-annotations", "j2objc.annotations")
    defineModule("org.codehaus.mojo:animal-sniffer-annotations", "animal.sniffer.annotations")
}
