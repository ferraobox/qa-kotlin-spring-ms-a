import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.liquibase.gradle") version "2.0.4"
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
	apply(plugin = "org.liquibase.gradle")
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

		//Jetbrains Kotlin
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		//Spring BOOT Framework
		implementation("org.springframework.boot:spring-boot-starter-validation")
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		//Spring CLOUD Framework
		implementation( "org.springframework.cloud:spring-cloud-starter-config")
		testImplementation("org.springframework.cloud:spring-cloud-stream-test-support")
		//Spring Kafka
		//implementation("org.springframework.kafka:spring-kafka")
		//implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")
		//testImplementation("org.springframework.kafka:spring-kafka-test")
		//Data Base migration tool
		implementation("org.liquibase:liquibase-core")
		implementation("org.liquibase:liquibase-gradle-plugin:2.0.4")
		liquibaseRuntime("org.liquibase.ext:liquibase-hibernate5:3.8")
		//Infra
		runtimeOnly("com.h2database:h2")
		runtimeOnly("io.micrometer:micrometer-registry-prometheus")
		runtimeOnly("org.postgresql:postgresql")
	}

	tasks.register("liquibase") {
		// depend on the liquibase status task
		dependsOn("run")
		liquibase {
			activities.register("main") {
				this.arguments = mapOf(
					"logLevel" to "info",
					"changeLogFile" to "application/src/main/resources/db/changelog/changelog-master.xml",
					"url" to "jdbc:h2:mem:qamyapp",
					"username" to "sa",
					"password" to "")
			}
		}
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