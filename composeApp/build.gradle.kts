plugins {
    id("gymhelper.android.application")
}

dependencies {
    implementation(projects.shared)

    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.coroutines.android)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
}
