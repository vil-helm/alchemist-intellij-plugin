plugins {
    application
    java
    kotlin("jvm") version "1.3.50"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("it.unibo.alchemist", "alchemist", "+") // Set your favorite version.
}

application.mainClassName = "it.unibo.alchemist.Alchemist"

sourceSets["main"].resources.srcDirs("src/main/yaml")