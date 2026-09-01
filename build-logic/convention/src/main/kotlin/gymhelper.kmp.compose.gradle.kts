import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    id("gymhelper.kmp.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.findBundle("compose").get())

            implementation(libs.findLibrary("lifecycle-viewmodel").get())
            implementation(libs.findLibrary("lifecycle-runtime-compose").get())
        }
    }
}
