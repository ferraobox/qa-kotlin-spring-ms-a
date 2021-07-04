package com.ferraobox.qamyapp.application.database.repositories

import com.ferraobox.qamyapp.application.database.entities.ProductDb
import org.springframework.data.jpa.repository.JpaRepository

interface DbProductRepository : JpaRepository<ProductDb?, Long?> {
    fun findByNameContainingOrDescriptionContainingAllIgnoreCase(name: String, description: String): List<ProductDb?>
    fun findByStoreIdAndIdIsIn(id: Long, ids: List<Long>): List<ProductDb?>
}