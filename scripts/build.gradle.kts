plugins {
    id("conventions")
}

dependencies {
    implementation(kotlin("script-runtime"))
    implementation(project(":core"))
}

sourceSets {
    main {
        java.srcDirs.clear()
        kotlin.setSrcDirs(listOf("lib"))
        resources.setSrcDirs(listOf("sample", "user"))
    }
    test {
        java.srcDirs.clear()
        kotlin.srcDirs.clear()
        resources.srcDirs.clear()
    }
}
