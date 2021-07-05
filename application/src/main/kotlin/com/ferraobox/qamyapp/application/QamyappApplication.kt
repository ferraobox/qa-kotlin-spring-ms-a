package com.ferraobox.qamyapp.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import javax.servlet.Servlet

@SpringBootApplication
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
