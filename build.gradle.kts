plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false // ✅ Mark this as `apply false`
    id("com.google.dagger.hilt.android") version "2.46.1" apply false // ✅ Apply only in module
}
