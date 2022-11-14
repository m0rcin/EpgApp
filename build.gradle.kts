buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven {
            setUrl("https://oss.sonatype.org/content/repositories/snapshots")
            content {
                includeModule("com.google.dagger", "hilt-android-gradle-plugin")
            }
        }
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra["hiltPluginVersion"]}")
        classpath("com.android.tools.build:gradle:${rootProject.extra["gradleBuildToolsVersion"]}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlinVersion"]}")
    }
}

plugins {
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}