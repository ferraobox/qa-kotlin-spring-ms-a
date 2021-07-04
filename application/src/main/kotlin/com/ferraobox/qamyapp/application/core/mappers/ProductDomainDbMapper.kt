package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.core.mappers.StoreDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.StoreDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.database.entities.ProductDb
import java.util.*
import kotlin.collections.ArrayList

object ProductDomainDbMapper {

    fun Product.mapToDb(): ProductDb {
        return ProductDb(
            id = this.id.number,
            name = this.name,
            description = this.description,
            price = this.price,
            store = this.store.mapToDb()
        )
    }

    fun List<Product>.mapToDb(): MutableSet<ProductDb> {
        val productListDb: MutableSet<ProductDb> = HashSet()
        forEach { product -> productListDb.add(product.mapToDb()) }
        return productListDb
    }

    fun ProductDb.mapToDomain(): Product {
        return Product(
            id = Identity(this.id!!),
            name = this.name,
            description = this.description,
            price = this.price,
            store = this.store.mapToDomain()
        )
    }

    fun MutableSet<ProductDb>.mapToDomain(): List<Product> {
        val productList = ArrayList<Product>()
        forEach { productDb -> productList.add(productDb.mapToDomain()) }
        return productList
    }
}