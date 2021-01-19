import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.4.21"
val kotlinCoroutinesVersion = "1.4.2"
val jacksonVersion = "2.10.1"
val placeholderApiVersion = "1.4"

plugins {
    kotlin("jvm") version "1.4.21"
}

group = "com.creeperface.nukkit.bedwars"
version = "1.0-SNAPSHOT"

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.apply {
    jvmTarget = "1.8"
    freeCompilerArgs = listOf("-Xmulti-platform")
}

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://repo.opencollab.dev/maven-releases/")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    compileOnly(kotlin("stdlib-jdk8", kotlinVersion))
    compileOnly(kotlin("reflect", kotlinVersion))
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
    compileOnly("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    compileOnly("com.creeperface.nukkit.kformapi:KFormAPI:1.0-SNAPSHOT")
    compileOnly("com.creeperface.nukkit.placeholderapi:PlaceholderAPI")
}