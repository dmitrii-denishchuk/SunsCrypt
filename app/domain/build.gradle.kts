plugins {
    `java-library`
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.javax.inject)
//    implementation(libs.kotlinx.coroutines.core)
//    testImplementation(libs.kotlinx.coroutines.test)
//    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit.jupiter)
}