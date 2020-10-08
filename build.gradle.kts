plugins {
    kotlin("jvm") version "1.4.10"
    id("antlr")
    application
    idea
}

repositories {
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    antlr("org.antlr:antlr4:4.8-1")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "mykotlin.parser.AppKt"
}

tasks.generateGrammarSource {
    outputDirectory = file("${outputDirectory.path}/mykotlin/parser/antlr")
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages")
    arguments.add("-package")
    arguments.add("mykotlin.parser.antlr")
}

tasks {
    "compileKotlin"{ dependsOn("generateGrammarSource") }
}
