import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    application
    kotlin("jvm") version "1.8.20"
}

group = "love.sola.nfc"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("script-util"))
    implementation(kotlin("compiler-embeddable"))
    implementation(kotlin("scripting-compiler-embeddable"))
    testImplementation(kotlin("test-junit5"))
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xjvm-default=all")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    create("scripts") {
        resources.setSrcDirs(emptyList<String>())
        java.setSrcDirs(emptyList<String>())
        kotlin.setSrcDirs(listOf("scripts"))
        compileClasspath += main.get().compileClasspath
        compileClasspath += main.get().output
    }
}
