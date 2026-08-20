import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.compose.ComposeExtension

plugins {
    id("gymhelper.kmp.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// В precompiled script plugin нет type-safe аксессора `compose`, берём расширение руками.
val compose = extensions.getByType<ComposeExtension>().dependencies

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)

            implementation(libs.findLibrary("lifecycle-viewmodel").get())
            implementation(libs.findLibrary("lifecycle-runtime-compose").get())
        }
    }
}
