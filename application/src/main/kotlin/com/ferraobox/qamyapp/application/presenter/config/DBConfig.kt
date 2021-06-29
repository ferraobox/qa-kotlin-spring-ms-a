package com.ferraobox.qamyapp.application.presenter.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["com.ferraobox.qamyapp.application.database.entities"])
@EnableJpaRepositories(basePackages = ["com.ferraobox.qamyapp.application.database.repositories"])
open class DBConfig {}