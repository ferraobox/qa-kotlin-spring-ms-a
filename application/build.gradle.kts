
plugins {
    id("application")
}

dependencies {
    implementation(rootProject)
    implementation(project(":dto"))
    implementation("io.jsonwebtoken:jjwt:0.9.0")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.security:spring-security-test")
}

application {
    mainClass.set("com.ferraobox.qamyapp.application.QamyappApplicationKt")
}
