plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "1.5.3"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.4.32"
}

version = "0.1"
group = "br.com.aluraflix"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("br.com.aluraflix.*")
    }
}

dependencies {
    kapt("io.micronaut.data:micronaut-data-processor")
    kapt("io.micronaut.openapi:micronaut-openapi")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut.security:micronaut-security")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.flyway:micronaut-flyway")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.swagger.core.v3:swagger-annotations")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.apache.logging.log4j:log4j-core:2.14.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.apache.logging.log4j:log4j-api:2.14.1")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
    testImplementation("org.assertj:assertj-core")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.mockito:mockito-core")

    implementation("io.micronaut.micrometer:micronaut-micrometer-core")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-statsd")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-prometheus")
}


application {
    mainClass.set("br.com.aluraflix.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

task("stage") {
    dependsOn("build", "clean")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
