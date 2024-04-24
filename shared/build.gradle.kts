
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64(),
        ).forEach {
            it.binaries.framework {
                export("dev.icerock.moko:resources:0.23.0")
                export("dev.icerock.moko:mvvm-core:0.16.1")
                export("dev.icerock.moko:mvvm-state:0.16.1")
                export("dev.icerock.moko:mvvm-flow:0.16.1")
                export("dev.icerock.moko:mvvm-flow-resources:0.16.1")
            }
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:mvvm-flow-resources:0.16.1")
            export("dev.icerock.moko:mvvm-core:0.16.1")
            export("dev.icerock.moko:mvvm-flow:0.16.1")
            export("dev.icerock.moko:mvvm-state:0.16.1")
        }
    }




    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false
            export("dev.icerock.moko:resources:0.23.0")
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            api(libs.io.koin.core)
            api(libs.kotlin.coroutines.core)

            // Ktor
            implementation(libs.io.ktor.client.core)
            implementation(libs.io.ktor.client.logging)
            implementation(libs.io.ktor.client.content.negotiation)
            implementation(libs.io.ktor.serialization.kotlinx.json)

            api("dev.icerock.moko:resources:0.23.0")
            api("dev.icerock.moko:mvvm-flow-resources:0.16.1")
            api("dev.icerock.moko:mvvm-core:0.16.1")
            api("dev.icerock.moko:mvvm-flow:0.16.1")
            api("dev.icerock.moko:mvvm-state:0.16.1")

            implementation("dev.gitlive:firebase-firestore:1.8.1") // This line
            implementation("dev.gitlive:firebase-storage:1.12.0") // This line
            implementation("dev.gitlive:firebase-common:1.8.1")// This line
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
        }

        val androidMain by getting{
            dependencies {
                implementation(libs.io.ktor.client.okhttp)
            }
            dependsOn(commonMain.get())
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        iosMain {
            dependsOn(commonMain.get())
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.io.ktor.client.darwin)
            }
        }
    }
}

android {
    namespace = "uz.uni_team.nihol"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.kotlin.coroutines.android)
}

multiplatformResources {
    multiplatformResourcesPackage = "uz.cdti.samplekmmapp" // required
    iosBaseLocalizationRegion = "en" // optional, default "en"
    disableStaticFrameworkWarning = true
}