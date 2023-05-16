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

    implementation ("com.google.firebase:firebase-admin:9.1.1")

    testImplementation(kotlin("test"))
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