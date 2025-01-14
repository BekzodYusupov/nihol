buildscript {
    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.23.0")
//        classpath("dev.icerock.moko:network-generator:0.21.0")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    id("com.google.gms.google-services") version "4.4.1" apply false
}
