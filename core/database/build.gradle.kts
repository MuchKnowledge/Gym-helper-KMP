plugins {
    id("gymhelper.kmp.room")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }
}
