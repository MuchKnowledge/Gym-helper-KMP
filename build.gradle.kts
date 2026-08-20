// Корневой build-файл намеренно пустой: вся общая конфигурация живёт
// в convention-плагинах (build-logic/convention), а не в allprojects/subprojects.
//
// ВАЖНО: не добавляй `alias(libs.plugins.*)` в build-файлы модулей.
// Плагины уже загружены classloader'ом included build (build-logic); объявление
// с версией в обычном модуле поднимает ВТОРОЙ экземпляр Kotlin Gradle Plugin,
// и сборка падает на "class X cannot be cast to class X" (разные classloader'ы).
// Нужен новый плагин — добавь его в build-logic/convention/build.gradle.kts
// и примени в соответствующем convention-плагине.
//
// detekt + ktlint подключим отдельным слайсом, когда каркас соберётся.

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
