plugins {
    application
    id("conventions")
}

application {
    mainModule.set("love.sola.nfc")
    mainClass.set("love.sola.nfc.SolaNFCKt")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(project(":kts"))
    implementation(kotlin("scripting-jvm-host"))
}
