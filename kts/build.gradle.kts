plugins {
    `java-library`
    id("conventions")
}

dependencies {
    implementation(kotlin("reflect"))
    api(kotlin("scripting-common"))
    implementation(kotlin("scripting-jvm"))
    implementation(kotlin("scripting-jvm-host"))
    implementation(kotlin("scripting-dependencies"))
    implementation(kotlin("scripting-dependencies-maven"))
    testImplementation(kotlin("test-junit5"))
}
