plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(libs.versions.jvmToolchain.get().toInt())
}

// Precompiled script plugins лежат в src/main/kotlin/<id>.gradle.kts —
// Gradle сам регистрирует id по имени файла, отдельный gradlePlugin {} блок не нужен.
dependencies {
    implementation(libs.gradle.plugin.kotlin)
    implementation(libs.gradle.plugin.kotlin.serialization)
    implementation(libs.gradle.plugin.android)
    implementation(libs.gradle.plugin.android.kmp.library)
    implementation(libs.gradle.plugin.compose)
    implementation(libs.gradle.plugin.compose.compiler)
    implementation(libs.gradle.plugin.ksp)
    implementation(libs.gradle.plugin.room)
    implementation(libs.gradle.plugin.ktlint)
}
