import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet


group = "com.kalvatn.aoc.aokotlin"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.3.41"
    `java-library`
    idea
    application
}
tasks.wrapper {
    gradleVersion = "5.4.1"
    distributionType = Wrapper.DistributionType.ALL
}
tasks.compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}



sourceSets {
    main {
        withConvention(KotlinSourceSet::class) {
            kotlin.srcDir("src/main/kotlin")
        }
        resources {
            srcDir("src/main/resources")
        }
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

dependencies {
    val versionKotlinCoroutines = "1.3.0-M2"
    val versionKotlintest = "3.3.2"
    val versionLogback = "1.2.1"
    val versionSlf4j = "1.7.7"
    val versionKtor = "1.2.2"
    val versionJackson = "2.9.9"


    val versionOkHttp = "4.0.0"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:$versionKotlinCoroutines")

    implementation("ch.qos.logback:logback-classic:$versionLogback")
    implementation("org.slf4j:slf4j-log4j12:$versionSlf4j")
    implementation("io.ktor:ktor-server-netty:$versionKtor")
    implementation("io.ktor:ktor-jackson:$versionKtor")
    implementation("io.ktor:ktor-locations:$versionKtor")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$versionJackson")


    implementation("com.squareup.okhttp3:okhttp:$versionOkHttp")
    implementation("com.squareup.okhttp3:logging-interceptor:$versionOkHttp")

    // test dependencies
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.50")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:$versionKotlintest")
}
val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

application {
    mainClassName = "com.kalvatn.aoc.aokotlin.RunnerKt"
}
