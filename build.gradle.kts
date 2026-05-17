plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = "cz.creeperface.hytale.bedwars"
version = "1.0"

dependencies {
    // kotlinx-serialization-json is provided by the Hytale runtime; never bundled.
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    compileOnly(kotlin("reflect", Kotlin.version))
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutinesVersion}")

    compileOnly(fileTree(rootProject.projectDir.resolve("libs")) { include("*.jar") })

    val userHomeDir = file(System.getProperty("user.home"))
    val osName = System.getProperty("os.name").lowercase()
    val hytaleBase = when {
        osName.contains("mac") -> "${userHomeDir.absolutePath}/Library/Application Support/Hytale"
        osName.contains("win") -> "${System.getenv("APPDATA")}/Hytale"
        else -> "${userHomeDir.absolutePath}/.local/share/Hytale"
    }
    compileOnly(files("$hytaleBase/install/release/package/game/latest/Server/HytaleServer.jar"))
}
