plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33
    buildToolsVersion = "${rootProject.extra["buildToolsVersion"]}"

    defaultConfig {
        applicationId = "co.proexe.epgapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        dataBinding = true
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion ="${rootProject.extra["compilerVer"]}"
    }

    namespace = "co.proexe"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlinVersion"]}")
    //hilt
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hiltPluginVersion"]}")
    implementation("androidx.hilt:hilt-navigation-compose:${rootProject.extra["hiltCompilerVersion"]}")
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.extra["hiltPluginVersion"]}")
    kapt("androidx.hilt:hilt-compiler:${rootProject.extra["hiltCompilerVersion"]}")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.accompanist:accompanist-swiperefresh:${rootProject.extra["swipeRefresh"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("androidx.activity:activity-ktx:${rootProject.extra["activityVersion"]}")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("junit:junit:4.13.2")

    implementation("androidx.core:core-ktx:${rootProject.extra["coreKtxVersion"]}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${rootProject.extra["lifecycleVersion"]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${rootProject.extra["lifecycleVersion"]}")

    implementation("androidx.activity:activity-compose:${rootProject.extra["composeActivityVersion"]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${rootProject.extra["lifecycleVersion"]}")
    implementation("com.google.android.material:compose-theme-adapter:${rootProject.extra["composeThemeAdapter"]}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${rootProject.extra["constraintLayoutCompose"]}")

    //Jetpack Compose using Bill of Materials (BOM)
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.foundation:foundation-layout")

    implementation(platform("androidx.compose:compose-bom:${rootProject.extra["composeBOM"]}"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-util")

    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")

    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.runtime:runtime-rxjava2")
    implementation("androidx.navigation:navigation-compose")
}