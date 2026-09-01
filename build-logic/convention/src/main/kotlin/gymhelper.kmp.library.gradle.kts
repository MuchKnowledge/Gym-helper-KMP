import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("gymhelper.quality")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * ":core:designsystem" -> "com.trulala.gymhelper.core.designsystem"
 * Держим namespace выводимым из пути модуля, чтобы он не разъезжался при добавлении модулей.
 */
/**
 * Kotlin/Native для Apple-таргетов линкуется только на macOS, поэтому вне неё
 * объявлять их бессмысленно: таски всё равно не запустятся, зато ломают
 * `check`, `build` и `ktlintCheck` на машине разработчика.
 * Переопределяется свойством `gymhelper.iosTargets` в gradle.properties.
 */
val buildIosTargets: Boolean = providers.gradleProperty("gymhelper.iosTargets")
    .map(String::toBoolean)
    .getOrElse(HostManager.hostIsMac)

val moduleNamespace: String = "com.trulala.gymhelper." +
    path.removePrefix(":").replace(':', '.').replace("-", "")

kotlin {
    jvmToolchain(libs.findVersion("jvmToolchain").get().requiredVersion.toInt())

    android {
        namespace = moduleNamespace
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }

        // Без этого у android-таргета нет тестовой компиляции: commonTest
        // висит неподключённым, а unit-тесты просто негде запускать.
        withHostTestBuilder {}.configure {}
    }

    // iosX64 намеренно отсутствует: Compose Multiplatform 1.11+ не поддерживает Apple x86_64.
    if (buildIosTargets) {
        iosArm64()
        iosSimulatorArm64()
    }

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
