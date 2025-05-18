plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

val tomtomApiKey: String by project
val tomtomRouteApiKey: String by project

android {
    namespace = "com.example.mirutaideal"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mirutaideal"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField("String", "TOMTOM_API_KEY", "\"$tomtomApiKey\"")
            buildConfigField("String", "TOMTOM_ROUTE_API_KEY", "\"$tomtomRouteApiKey\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "TOMTOM_API_KEY", "\"$tomtomApiKey\"")
            buildConfigField("String", "TOMTOM_ROUTE_API_KEY", "\"$tomtomRouteApiKey\"")
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.provider.default)
    implementation(libs.provider.map.matched)
    implementation(libs.provider.simulation)
    implementation(libs.provider.api)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compilercommon)
    implementation("com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava")
    implementation(libs.play.services.location)
    implementation(libs.androidx.appcompat)
    implementation(libs.litert.metadata)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.play.services.vision)
    val version = "1.24.0"
    implementation("com.tomtom.sdk.navigation:navigation-online:$version")
    implementation("com.tomtom.sdk.maps:map-display:1.24.0")
    implementation(libs.androidx.ui.tooling.preview)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    implementation("com.stripe:stripe-android:20.3.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation(libs.material)
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.tomtom.sdk.routing:route-planner-online:1.24.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
