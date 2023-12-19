plugins {
    kotlin("jvm") version "1.9.21"
}

group = "xyz.andornot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(JavaVersion.VERSION_17.toString())
    }

    targetCompatibility = JavaVersion.VERSION_17
    sourceCompatibility = JavaVersion.VERSION_17
}

dependencies {
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}