plugins {
    java
    id("io.quarkus")
    kotlin("jvm")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkiverse.openfga:quarkus-openfga-client:1.2.0")
    implementation("io.quarkiverse.zanzibar:quarkus-zanzibar-openfga:1.2.0")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.quarkus:quarkus-test-security")
    testImplementation("io.rest-assured:rest-assured")
    implementation(kotlin("stdlib-jdk8"))
}

group = "org.example"
version = "1.0.0-SNAPSHOT"

java {
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
kotlin {
    jvmToolchain(21)
}