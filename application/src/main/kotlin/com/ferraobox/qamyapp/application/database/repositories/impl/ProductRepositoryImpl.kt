package com.ferraobox.qamyapp.application.database.repositories.impl

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.core.mappers.ProductDomainDbMapper
import com.ferraobox.qamyapp.application.core.repositories.IProductRepository
import com.ferraobox.qamyapp.application.database.repositories.DbProductRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Collectors
import kotlin.streams.toList

@Repository
class ProductRepositoryImpl(
    private val repository: DbProductRepository,
    private val productDomainDbMapper: ProductDomainDbMapper
) : IProductRepository {

    override fun all(): List<Product>? {
        return repository
            .findAll()
            .stream()
            .map { p -> productDomainDbMapper.mapToDomain(p) }
            .collect(Collectors.toList<Product>())
    }

    override fun getById(id: Identity?): Optional<Product> {
        return repository
            .findById(id!!.number)
            .map { p -> productDomainDbMapper.mapToDomain(p) }
    }

    override fun searchByNameOrDescription(searchText: String?): List<Product> {
        val products = repository.findByNameContainingOrDescriptionContainingAllIgnoreCase(searchText, searchText)
        return products!!.stream()
            .map { p -> productDomainDbMapper.mapToDomain(p) }
            .collect(Collectors.toList<Product>())
    }

    override fun searchProductsByStoreAndProductsId(storeId: Identity?, productsId: List<Identity>?): List<Product> {
        val products = repository.findByStoreIdAndIdIsIn(storeId!!.number, createListOfLong(productsId!!))
        return products!!.stream()
            .map { p -> productDomainDbMapper.mapToDomain(p) }
            .collect(Collectors.toList<Product>())
    }

    private fun createListOfLong(productsId: List<Identity>): List<Long> {
        return productsId
            .stream()
            .map { it.number }.toList<Long>()
    }
}
