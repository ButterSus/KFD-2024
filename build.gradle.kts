plugins {
    kotlin("jvm") version "2.0.10"
    id("application")
}

group = "com.buttersus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("com.buttersus.MainKt")
}
