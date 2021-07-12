

dependencies {
    //Versions
    val serenityVersion = "2.4.48"
    val serenityCucumberVersion = "2.4.48"
    val jacksonVersion = "2.12.4"
    val gatlingVersion = "3.1.2"
    val wiremockJre8StandaloneVersion = "2.27.2"
    val jsonAssertVersion = "1.5.0"
    val assertjCoreVersion = "3.20.2"
    val junitVersion = "5.8.0-M1"
    //Dependencies
    implementation(rootProject)
    implementation(kotlin("stdlib"))
    //JUNIT 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    //Serenity
    implementation("net.serenity-bdd:serenity-core:$serenityVersion")
    implementation("net.serenity-bdd:serenity-single-page-report:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-junit:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-rest-assured:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-screenplay-rest:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-cucumber6:$serenityCucumberVersion")
    //XML
    testImplementation("com.fasterxml.jackson.module:jackson-module-jaxb-annotations:$jacksonVersion")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    //Gatling (Performance test)
    testImplementation("io.gatling.highcharts:gatling-charts-highcharts:$gatlingVersion")
    //Wiremock
    testImplementation("com.github.tomakehurst:wiremock-jre8-standalone:$wiremockJre8StandaloneVersion")
    //Json assert (OAS test)
    testImplementation("org.skyscreamer:jsonassert:$jsonAssertVersion")
    testImplementation("org.assertj:assertj-core:$assertjCoreVersion")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}