plugins {
    kotlin("jvm") version "1.8.10"
    id("java")
    `maven-publish`
    id ("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "com.zorbeytorunoglu"
version = "0.0.5"

repositories {
    mavenCentral()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.apache.maven:maven-artifact:3.8.7")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
}

tasks {

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

}

tasks.shadowJar.configure {
    archiveClassifier.set("")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.zorbeytorunoglu"
            artifactId = "kLib"
            version = "0.0.5"

            from(components["java"])
        }
    }
}