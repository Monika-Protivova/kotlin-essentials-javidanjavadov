plugins {
    kotlin("jvm") version "1.9.22"
    application
}


group = "com.motycka.edu"
version = "0.0.1"

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // KotlinLogging
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
    // SLF4J implementation (Logback)
    implementation("ch.qos.logback:logback-classic:1.5.13")

    // Kotest
    val kotestVersion = "5.8.0"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}

tasks.test {
    useJUnitPlatform()
}

// Create test tasks for each lesson
// Usage: ./gradlew testLesson01, ./gradlew testLesson02, etc.
listOf(1, 2, 3, 4).forEach { lessonNumber ->
    val lessonNumberFormatted = String.format("%02d", lessonNumber)
    tasks.register<Test>("testLesson$lessonNumberFormatted") {
        description = "Runs tests for Lesson $lessonNumberFormatted"
        group = "verification"

        useJUnitPlatform()

        // Include only tests from the specific lesson
        filter {
            includeTestsMatching("com.motycka.edu.lesson$lessonNumberFormatted.*")
        }

        // Show test results on the console
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

// Custom task to run only AreaCalculatorTest
tasks.register<Test>("testAreaCalculator") {
    description = "Runs only AreaCalculatorTest"
    group = "verification"

    useJUnitPlatform()

    // Include only AreaCalculatorTest
    filter {
        includeTestsMatching("com.motycka.edu.lesson03.AreaCalculatorTest")
    }

    // Show test results on the console
    testLogging {
        events("passed", "skipped", "failed")
    }
}
application {
    mainClass.set("com.motycka.edu.lesson01.MainKt")
}
