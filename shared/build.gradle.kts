plugins {
    id("gymhelper.kmp.compose")
}

kotlin {
    // Фреймворк для будущего iosApp. Xcode-проект появится, когда будет Mac,
    // но baseName фиксируем сейчас, чтобы имя не менялось задним числом.
    listOf(iosArm64(), iosSimulatorArm64()).forEach { target ->
        target.binaries.framework {
            baseName = "GymHelperKMP"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            api(projects.core.designsystem)

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
