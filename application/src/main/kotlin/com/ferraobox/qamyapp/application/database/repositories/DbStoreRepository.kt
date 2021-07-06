package com.ferraobox.qamyapp.application.database.repositories

import com.ferraobox.qamyapp.application.database.entities.ProductDb
import com.ferraobox.qamyapp.application.database.entities.StoreDb
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DbStoreRepository : JpaRepository<StoreDb, Long> {
    fun findByNameContainingIgnoreCase(name: String?): List<StoreDb?>

    @Query("select p from store s join s.products p where s.id = ?1")
    fun findProductsById(id: Long): List<ProductDb?>

    @Query("select s from cousine c join c.stores s where c.id = ?1")
    fun findStoresById(id: Long): List<StoreDb?>
}