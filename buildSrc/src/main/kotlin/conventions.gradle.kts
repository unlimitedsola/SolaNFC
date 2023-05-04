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
    addModules()
}
