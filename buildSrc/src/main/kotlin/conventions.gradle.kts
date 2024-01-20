import jpms.addModules
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

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xjvm-default=all")
    }
}

// JPMS hack, see https://github.com/gradle/gradle/issues/17271
val compileJava: JavaCompile by tasks
val compileKotlin: KotlinCompile by tasks
compileKotlin.destinationDirectory.set(compileJava.destinationDirectory)

tasks.withType<Test> {
    useJUnitPlatform()
}

extraJavaModuleInfo {
    addModules()
}
