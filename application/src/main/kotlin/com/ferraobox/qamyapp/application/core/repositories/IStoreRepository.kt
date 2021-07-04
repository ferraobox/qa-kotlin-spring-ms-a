package com.ferraobox.qamyapp.application.core.repositories

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.core.domain.Store
import java.util.*

interface IStoreRepository {
    fun all(): List<Store>
    fun searchByName(searchText: String): List<Store>
    fun getById(id: Identity): Optional<Store>
    fun getProductsById(id: Identity): List<Product>
    fun getStoresById(id: Identity): List<Store>
}