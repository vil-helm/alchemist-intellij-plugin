plugins {
    kotlin("jvm") version "1.3.31"
    id("org.jetbrains.intellij") version "0.4.9"
}

repositories {
    mavenCentral()
}

dependencies { 
    implementation("io.github.classgraph","classgraph","4.8.43")
}

intellij {
    version = "2019.1"
    setPlugins("gradle")
}