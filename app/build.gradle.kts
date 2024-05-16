import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

fun getBaseUrl(propertyBaseUrl : String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyBaseUrl)
}

android {
    namespace = "com.example.remind"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.remind"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", getBaseUrl("BASE_URL"))
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", "\"${getBaseUrl("KAKAO_NATIVE_APP_KEY")}\"")
        manifestPlaceholders["kakaoKey"] = "kakao${getBaseUrl("KAKAO_NATIVE_APP_KEY")}"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.benchmark:benchmark-macro:1.2.4")
    implementation("com.google.android.engage:engage-core:1.5.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    //Retrofit, okhttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.8.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.8.0")

    //kakao login
    implementation("com.kakao.sdk:v2-user:2.20.1")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-messaging:23.2.1")

    //navigation
    implementation("androidx.navigation:navigation-compose:2.4.2")

    //ted permission
    implementation("io.github.ParkSangGwon:tedpermission-normal:3.3.0")
    implementation("io.github.ParkSangGwon:tedpermission-coroutine:3.3.0")

    //data store
    implementation( "androidx.datastore:datastore-preferences:1.1.0")

    //splash screen api
    implementation( "androidx.core:core-splashscreen:1.0.1")

    //graph library
    implementation("com.github.jaikeerthick:Composable-Graphs:v1.2.3")

    //serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    //timber
    implementation ("com.jakewharton.timber:timber:5.0.1")

    //lifecycle
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01")

    //chucker
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    //landscapist - glide
    implementation("com.github.skydoves:landscapist-glide:2.3.3")
    implementation ("com.github.bumptech.glide:compose:1.0.0-alpha.1")

    //tabrow
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")
}