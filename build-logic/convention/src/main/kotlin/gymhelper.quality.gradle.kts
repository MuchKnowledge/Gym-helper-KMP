import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    id("org.jlleitschuh.gradle.ktlint")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

extensions.configure<KtlintExtension> {
    // Версию ktlint пинуем явно: иначе плагин тянет свою, и форматирование
    // разъезжается между машиной разработчика и CI.
    version.set(libs.findVersion("ktlint").get().requiredVersion)
    android.set(false)
    ignoreFailures.set(false)

    // filter { exclude { ... } } здесь намеренно НЕ используется: лямбда-спека
    // захватывает объект скрипта и ломает configuration cache. Сгенерированный
    // код отсекаем через секцию [**/build/**] в .editorconfig — её читает сам ktlint.
}
