plugins {
    id("com.android.application")
}

android {
    namespace = "layout.pizza"
    compileSdk = 35

    defaultConfig {
        applicationId = "layout.pizza"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
}
