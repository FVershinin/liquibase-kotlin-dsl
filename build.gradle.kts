import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
}

group = "org.liquibase"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.liquibase:liquibase-core:3.8.1")
    implementation(kotlin("stdlib-jdk8"))
    runtime("com.h2database:h2:1.4.200")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}