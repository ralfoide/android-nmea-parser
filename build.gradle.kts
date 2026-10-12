plugins {
    id("com.android.library")
    id("jacoco")
}

android {
    namespace = "com.github.petr_s.nmea"
    compileSdk = 37

    enableKotlin = false

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        getByName("debug") {
            // testCoverageEnabled = true
        }
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.20.0")
}
