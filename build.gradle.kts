import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.10"
	kotlin("plugin.spring") version "1.5.10"
	kotlin("plugin.jpa") version "1.5.10"
}

tasks.bootJar {enabled = false}
tasks.jar {enabled = true}

repositories {
	mavenCentral()
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects{
	group = "com.ferraobox"
	version = "0.0.1-SNAPSHOT"
}

subprojects{
	apply(plugin="org.springframework.boot")
	apply(plugin="io.spring.dependency-management")
	apply(plugin="kotlin")
	apply(plugin="groovy")

	repositories {
		mavenCentral()
	}

	dependencyManagement {
		imports {
			mavenBom("org.springframework.cloud:spring-cloud-dependencies:2020.0.3")
		}
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-validation")
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("org.liquibase:liquibase-core")
		implementation("org.springframework.kafka:spring-kafka")
		implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")

		implementation( "org.springframework.cloud:spring-cloud-starter-config")
		implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

		runtimeOnly("com.h2database:h2")
		runtimeOnly("io.micrometer:micrometer-registry-prometheus")
		runtimeOnly("org.postgresql:postgresql")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.springframework.kafka:spring-kafka-test")
		testImplementation("org.springframework.cloud:spring-cloud-stream-test-support")

	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

project(":application"){
	tasks.bootJar{ enabled = true }
}
project(":dto"){
	tasks.bootJar{ enabled = false }
}