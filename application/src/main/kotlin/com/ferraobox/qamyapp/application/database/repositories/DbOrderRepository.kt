package com.ferraobox.qamyapp.application.database.repositories

import com.ferraobox.qamyapp.application.database.entities.OrderDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbOrderRepository : JpaRepository<OrderDb, Long>