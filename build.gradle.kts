plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.github.petr_s.nmea.fork"
    compileSdk = 37

    enableKotlin = false

    defaultConfig {
        minSdk = 21
    }
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
}
