import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    id("org.jetbrains.intellij") version "0.4.9"
    id("com.gradle.build-scan") version "2.4.2"
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

buildScan {

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}