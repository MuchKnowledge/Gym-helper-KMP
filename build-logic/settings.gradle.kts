pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    // Included build резолвит toolchain отдельно от корневой сборки, поэтому
    // foojay нужен и здесь — иначе jvmToolchain(17) падает с
    // "Toolchain download repositories have not been configured".
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"

include(":convention")
