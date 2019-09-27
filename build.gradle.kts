import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    id("org.jlleitschuh.gradle.ktlint") version "8.2.0"
    id("org.jetbrains.dokka") version "0.9.18"
    id("org.jetbrains.intellij") version "0.4.9"
    id("com.gradle.build-scan") version "2.4.2"
    id("de.fayard.buildSrcVersions") version "0.6.1"
    id("org.danilopianini.git-sensitive-semantic-versioning") version "0.2.1"
}

repositories {
    jcenter()
}

dependencies {
    implementation("io.github.classgraph", "classgraph", "4.8.43")
}

intellij {
    version = "2019.1"
    setPlugins("gradle")
}

buildScan {
}

gitSemVer {
    version = computeGitSemVer() // THIS IS MANDATORY, AND MUST BE LAST IN BLOCK
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}