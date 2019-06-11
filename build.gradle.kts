plugins {
    kotlin("jvm") version "1.3.31"
    id("org.jetbrains.intellij") version "0.4.9"
}

repositories { mavenCentral() }

dependencies { compile("org.reflections", "reflections", "0.9.11") }

intellij {
    version = "2019.1"
    setPlugins("gradle")
}