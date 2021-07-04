package com.ferraobox.qamyapp.application.core.repositories

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Product
import java.util.*

interface IProductRepository {
    fun all(): List<Product>
    fun getById(id: Identity): Optional<Product>
    fun searchByNameOrDescription(searchText: String): List<Product>
    fun searchProductsByStoreAndProductsId(storeId: Identity, productsId: List<Identity>): List<Product>
}