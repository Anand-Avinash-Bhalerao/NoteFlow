// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.googleDaggerHilt) apply false
    alias(libs.plugins.googleDevtoolKsp) apply false
    alias(libs.plugins.android.library) apply false
}