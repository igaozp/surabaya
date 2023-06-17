plugins {
    kotlin("jvm") version "1.8.22"
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