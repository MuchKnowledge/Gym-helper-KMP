import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

extensions.configure<ApplicationExtension> {
    namespace = "com.trulala.gymhelper"
    compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()

    defaultConfig {
        applicationId = "com.trulala.gymhelper"
        minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
        targetSdk = libs.findVersion("android-targetSdk").get().requiredVersion.toInt()
        versionCode = 1
        versionName = "0.1.0"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// AGP 9 несёт встроенную поддержку Kotlin, поэтому org.jetbrains.kotlin.android не применяем.
extensions.configure<KotlinAndroidProjectExtension> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}
