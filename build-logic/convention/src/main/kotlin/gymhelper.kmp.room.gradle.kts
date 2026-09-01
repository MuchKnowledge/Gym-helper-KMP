import androidx.room3.gradle.RoomExtension
import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    id("gymhelper.kmp.library")
    id("com.google.devtools.ksp")
    id("androidx.room3")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

// Схемы коммитим: они нужны для тестов миграций начиная с первой же версии БД.
// Аксессор `room { }` Gradle генерирует только для build-файла, в plugins-блоке
// которого объявлен плагин; в precompiled-скрипте берём расширение по типу.
extensions.configure<RoomExtension> {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    // JVM-таргет здесь — ради тестов: JVM-артефакт Room + BundledSQLiteDriver
    // позволяют гонять DAO-тесты без Context, Robolectric и эмулятора.
    // Модули, от которых зависит Room-модуль, тоже должны объявлять jvm().
    jvm()

    sourceSets {
        commonMain.dependencies {
            api(libs.findLibrary("room-runtime").get())
            implementation(libs.findLibrary("sqlite-bundled").get())
        }
    }
}

// Room-процессор подключается к каждому таргету отдельно:
// общего kspCommonMain для него не существует.
dependencies {
    val roomCompiler = libs.findLibrary("room-compiler").get()
    add("kspAndroid", roomCompiler)
    add("kspJvm", roomCompiler)

    // iOS-таргеты объявлены не на всех хостах, а вместе с ними отсутствуют
    // и их ksp-конфигурации — поэтому подключаемся только к существующим.
    listOf("kspIosArm64", "kspIosSimulatorArm64")
        .filter { configurations.findByName(it) != null }
        .forEach { configurationName -> add(configurationName, roomCompiler) }
}
