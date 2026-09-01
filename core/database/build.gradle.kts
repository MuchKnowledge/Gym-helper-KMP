plugins {
    id("gymhelper.kmp.library")
    // Плагины без версии: они уже на classpath через included build build-logic.
    // Применяем их здесь, а не в convention-плагине, чтобы получить type-safe
    // аксессор `room { }` — Gradle генерирует его только для plugins-блока этого файла.
    id("com.google.devtools.ksp")
    id("androidx.room3")
}

room {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)

            api(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }
}

// Room-процессор нужен для каждого таргета отдельно: общего kspCommonMain для него нет.
dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
}
