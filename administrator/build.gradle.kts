plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = "co.geeksempire"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation ("com.google.firebase:firebase-admin:9.3.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0-RC")

    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}