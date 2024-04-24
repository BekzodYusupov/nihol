plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("com.google.gms.google-services")
}


android {
    namespace = "uz.uni_team.nihol.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.bekhzodnurmukhamedov.nihol"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)

    //  Koin
    implementation(libs.io.koin.android)
    implementation(libs.io.koin.compose)
    implementation(project.dependencies.platform("com.google.firebase:firebase-bom:30.0.1")) //
    implementation(libs.firebase.common.ktx)
    implementation("com.google.firebase:firebase-storage:20.3.0")
}