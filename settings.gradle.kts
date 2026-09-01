@file:Suppress("UnstableApiUsage")

rootProject.name = "gym-helper-kmp"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    // Ускоряет конфигурацию: Gradle сам подтягивает нужный JDK, не полагаясь на JAVA_HOME разработчика.
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// --- entry points ---
include(":composeApp")   // Android-приложение (com.android.application), тонкая обёртка
include(":shared")       // общий корень: RootComponent (Decompose) + RootContent + сборка DI-графа

// --- core ---
include(":core:common")
include(":core:designsystem")
include(":core:database")

// Дальше по мере наполнения — раскомментируем, когда у модуля появится build-файл:
// include(":core:ui")
// include(":core:datastore")
// include(":core:sync")
// include(":core:testing")
// include(":feature:workout")
// include(":feature:meals")
// include(":feature:tracking")
// include(":feature:analytics")
