# Gym Helper KMP

[![CI](https://github.com/MuchKnowledge/Gym-helper-KMP/actions/workflows/ci.yml/badge.svg)](https://github.com/MuchKnowledge/Gym-helper-KMP/actions/workflows/ci.yml)

Нативное мультиплатформенное приложение для тренировок, рациона и трекинга.
Android + iOS из одного Kotlin-кода: Compose Multiplatform, MVI, Clean Architecture.

> 🚧 В разработке. Каркас собран, дальше — первая фича (тренировки).

## Стек

| Слой | Решение |
|---|---|
| Язык | Kotlin 2.4.10 (Multiplatform) |
| UI | Compose Multiplatform 1.11.1, Material 3, весь UI в `commonMain` |
| Навигация | Decompose 3.5.0 (компонентная, KMP) |
| Состояние | MVIKotlin 4.4.0 — Store / Executor / Reducer, unidirectional data flow |
| DI | Koin 4.2.2 |
| БД | Room 3.0.1 (KMP) + bundled SQLite driver |
| Асинхронность | Coroutines + Flow |
| Сборка | Gradle 9.5 KTS, version catalog, convention plugins в `build-logic/` |

## Архитектура

```
composeApp/   Android-приложение: Application, MainActivity, старт Koin
iosApp/       iOS-приложение (Xcode)
shared/       корень: RootComponent (Decompose) + RootContent + сборка DI-графа
core/         common · designsystem · ui · database · datastore · sync · testing
feature/      workout · meals · tracking · analytics
```

Каждая фича — самостоятельный модуль: `domain` (модели + use cases) → MVI-store →
Decompose-компонент → Compose-UI. Платформенный код держится минимальным
и живёт только в `androidMain` / `iosMain`.

## Сборка

```bash
./gradlew ktlintCheck                  # стиль
./gradlew :composeApp:assembleDebug    # сборка
./gradlew allTests                     # тесты
```

Требуется JDK 17 и Android SDK с compileSdk 37.

## Качество кода

ktlint 1.8 с `ktlint_official`, подключается ко всем модулям через convention-плагин
`gymhelper.quality`. Правила — в `.editorconfig`, там же объяснены отступления от дефолта.
detekt 2.x появится, когда выйдет стабильный: 1.x не читает метаданные Kotlin 2.3+.

## Заметки о версиях

- `iosX64` не поддерживается: Compose Multiplatform 1.11+ отказался от Apple x86_64.
  Таргеты — `iosArm64` и `iosSimulatorArm64`.
- AGP 9 несовместим с `androidTarget()`: shared-модули используют
  `com.android.kotlin.multiplatform.library` + `kotlin { androidLibrary { } }`,
  Android-приложение вынесено в отдельный модуль без KMP-плагина.
- Room 3 — новая группа `androidx.room3`, KSP обязателен, DAO-функции только
  `suspend` или возвращают `Flow`.

## Лицензия

TBD
