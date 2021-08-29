buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildLibs.AndroidGradlePlugin)
        classpath(BuildLibs.KotlinPlugin)
        classpath(BuildLibs.ApolloPlugin)
    }
}

plugins {
    id("com.diffplug.spotless") version "5.14.3"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint("0.42.1")
        }
    }
}
