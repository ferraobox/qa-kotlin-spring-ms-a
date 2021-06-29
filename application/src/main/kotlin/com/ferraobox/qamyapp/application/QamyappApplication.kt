package com.ferraobox.qamyapp.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
open class QamyappApplication

fun main(args: Array<String>) {
	runApplication<QamyappApplication>(*args)
	println("Hello application running!!")
}
