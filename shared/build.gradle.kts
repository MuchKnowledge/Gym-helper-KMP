import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("gymhelper.kmp.compose")
}

kotlin {
    // Фреймворк для будущего iosApp. Вешаем на все native-таргеты, какие объявлены:
    // на не-macOS хостах их нет вовсе, и блок просто ничего не делает.
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "GymHelperKMP"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            api(projects.core.designsystem)
            api(projects.core.database)

            // api, а не implementation: типы Decompose (ComponentContext, Value, ChildStack)
            // торчат в публичном API RootComponent, значит потребители модуля обязаны их видеть.
            api(libs.bundles.decompose)
            implementation(libs.bundles.mvikotlin)
            implementation(libs.kotlinx.serialization.json)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
        }
    }
}
