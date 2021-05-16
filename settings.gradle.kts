pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}

plugins {
    id("com.gradle.enterprise") version "3.6.1"
    id("de.fayard.refreshVersions") version "0.10.1-LOCAL-SNAPSHOT"
}


refreshVersions {
    enableBuildSrcLibs()

    rejectVersionIf {
        versionKey != "version.kotlinx.coroutines" && candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
    }
}

// https://dev.to/jmfayard/the-one-gradle-trick-that-supersedes-all-the-others-5bpg
gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
        buildScanPublished {
            file("buildscan.log").appendText("${java.util.Date()} - $buildScanUri\n")
        }
    }

}

rootProject.name = "kotlin-libraries-playground"


include("kotlin-jvm")
include("kotlin-testing")
include("kotlin-codegen")
