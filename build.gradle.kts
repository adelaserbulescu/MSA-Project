val ktor_version = "3.3.2"
val logback_version = "1.5.13"
val mysql_version = "8.0.33"
val exposed_version = "0.53.0"
val openapi_version = "5.4.0"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

plugins {
    kotlin("jvm") version "2.2.20"
    id("io.ktor.plugin") version "3.0.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // Ktor
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Database
    implementation("mysql:mysql-connector-java:$mysql_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:${exposed_version}")

    // OpenAPI & Swagger
    implementation("io.github.smiley4:ktor-openapi:${openapi_version}")
    implementation("io.github.smiley4:ktor-swagger-ui:${openapi_version}")
    implementation("io.github.smiley4:ktor-redoc:${openapi_version}")


    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}