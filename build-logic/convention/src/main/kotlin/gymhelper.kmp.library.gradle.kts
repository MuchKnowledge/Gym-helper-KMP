import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * ":core:designsystem" -> "com.trulala.gymhelper.core.designsystem"
 * Держим namespace выводимым из пути модуля, чтобы он не разъезжался при добавлении модулей.
 */
val moduleNamespace: String = "com.trulala.gymhelper." +
    path.removePrefix(":").replace(':', '.').replace("-", "")

kotlin {
    jvmToolchain(libs.findVersion("jvmToolchain").get().requiredVersion.toInt())

    androidLibrary {
        namespace = moduleNamespace
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    // iosX64 намеренно отсутствует: Compose Multiplatform 1.11+ не поддерживает Apple x86_64.
    iosArm64()
    iosSimulatorArm64()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.findLibrary("kotlinx-coroutines-core").get())
        }
        commonTest.dependencies {
            implementation(libs.findLibrary("kotlin-test").get())
            implementation(libs.findLibrary("kotlinx-coroutines-test").get())
            implementation(libs.findLibrary("turbine").get())
        }
    }
}
