package com.ferraobox.qamyapp.application.database.repositories

import com.ferraobox.qamyapp.application.database.entities.CousineDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbCousineRepository : JpaRepository<CousineDb, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<CousineDb?>
}