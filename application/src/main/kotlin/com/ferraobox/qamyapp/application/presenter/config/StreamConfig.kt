package com.ferraobox.qamyapp.application.presenter.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
open class StreamConfig {
    @Bean("execute-consumer")
    open fun consume(): Consumer<String> {
        return Consumer { data: String -> println("Received: $data") }
    }
}