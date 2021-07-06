package com.ferraobox.qamyapp.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import javax.servlet.Servlet

@SpringBootApplication(
    scanBasePackages = ["com.ferraobox.qamyapp.application.core.usecases", "com.ferraobox.qamyapp.application.core.mappers", "com.ferraobox.qamyapp.application.presenter", "com.ferraobox.qamyapp.application.presenter.mappers","com.ferraobox.qamyapp.application.presenter.rest.api", "com.ferraobox.qamyapp.application.database"],
)
open class QamyappApplication

fun main(args: Array<String>) {
    runApplication<QamyappApplication>(*args)
    println("Hello application running!!")
}

@Bean
fun h2servletRegistration(): ServletRegistrationBean<*> {
    val registration: ServletRegistrationBean<*> = ServletRegistrationBean<Servlet>()
    registration.addUrlMappings("/console/*")
    return registration
}
