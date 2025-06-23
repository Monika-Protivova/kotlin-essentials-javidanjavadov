import java.io.File

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
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
    implementation("ch.qos.logback:logback-classic:1.5.13")
    val kotestVersion = "5.8.0"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}

tasks.test {
    useJUnitPlatform()
}

val lessonsDir = file("src/main/kotlin/com/motycka/edu")

val allRunTasks = mutableListOf<String>()

lessonsDir.listFiles { file -> file.isDirectory && file.name.startsWith("lesson") }?.forEach { lessonFolder ->
    val lessonName = lessonFolder.name
    val lessonNumberFormatted = lessonName.removePrefix("lesson")
    val lessonRunTasks = mutableListOf<String>()

    lessonFolder.listFiles { file -> file.isFile && file.extension == "kt" }?.forEach { ktFile ->
        val fileName = ktFile.nameWithoutExtension
        val taskName = "run${lessonName.replaceFirstChar { it.uppercase() }}_$fileName"
        lessonRunTasks += taskName
        allRunTasks += taskName

        tasks.register<JavaExec>(taskName) {
            group = "application"
            description = "Runs $fileName.kt in $lessonName"
            classpath = sourceSets["main"].runtimeClasspath
            mainClass.set("com.motycka.edu.$lessonName.${fileName}Kt")
        }
    }

    val lessonTaskName = "run${lessonName.replaceFirstChar { it.uppercase() }}"
    tasks.register(lessonTaskName) {
        group = "application"
        description = "Runs all files in $lessonName"
        dependsOn(lessonRunTasks)
    }

    tasks.register<Test>("testLesson$lessonNumberFormatted") {
        description = "Runs tests for $lessonName"
        group = "verification"
        useJUnitPlatform()
        filter {
            includeTestsMatching("com.motycka.edu.$lessonName.*")
        }
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

tasks.register("runAllLessons") {
    group = "application"
    description = "Runs all lesson files"
    dependsOn(allRunTasks)
}
