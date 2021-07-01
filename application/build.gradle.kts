import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    id("application")
}

dependencies {
    implementation(rootProject)
    implementation(project(":dto"))
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
    kapt("org.mapstruct:mapstruct-jdk8:1.4.2.Final")
//new
    implementation("io.jsonwebtoken:jjwt:0.2")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security:spring-security-test")
}

application {
    mainClass.set("com.ferraobox.qamyapp.application.QamyappApplication")
}
