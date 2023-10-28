plugins {
    kotlin("jvm") version "1.9.10"
}

group = "xyz.andornot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}