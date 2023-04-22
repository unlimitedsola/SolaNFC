import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
    id("org.gradlex.extra-java-module-info")
}

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit5"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

// JPMS hack, see https://github.com/gradle/gradle/issues/17271
val compileJava: JavaCompile by tasks
val compileKotlin: KotlinCompile by tasks
compileKotlin.destinationDirectory.set(compileJava.destinationDirectory)

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xjvm-default=all")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

extraJavaModuleInfo {
    module("org.jetbrains.kotlin:kotlin-scripting-common", "kotlin.scripting.common") {
        exportAllPackages()
        requires("kotlin.stdlib")
    }
    module("org.jetbrains.kotlin:kotlin-scripting-jvm", "kotlin.scripting.jvm") {
        exportAllPackages()
        requires("kotlin.stdlib")
        requires("kotlin.scripting.common")
        requires("kotlin.script.runtime")
    }
    automaticModule("org.jetbrains.kotlin:kotlin-scripting-jvm-host", "kotlin.scripting.jvm.host")
    module("org.jetbrains.kotlin:kotlin-scripting-dependencies", "kotlin.scripting.dependencies") {
        exportAllPackages()
        requires("kotlin.stdlib")
        requires("kotlin.scripting.common")
    }
    module("org.jetbrains.kotlin:kotlin-scripting-dependencies-maven", "kotlin.scripting.dependencies.maven") {
        exportAllPackages()
        requires("kotlin.scripting.dependencies")
    }
    automaticModule("org.jetbrains.kotlin:kotlin-script-runtime", "kotlin.script.runtime")
    automaticModule("org.jetbrains.kotlin:kotlin-stdlib-common", "kotlin.stdlib.common")
    automaticModule("org.jetbrains:annotations", "org.jetbrains.annotations")
}
