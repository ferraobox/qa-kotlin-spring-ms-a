package com.ferraobox.qamyapp.application.database.repositories.impl

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.core.domain.Store
import com.ferraobox.qamyapp.application.core.mappers.ProductDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.mappers.StoreDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.repositories.IStoreRepository
import com.ferraobox.qamyapp.application.database.repositories.DbStoreRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.stream.Collectors


@Repository
open class StoreRepositoryImpl(
    private val repository: DbStoreRepository,
) : IStoreRepository {

    override fun all(): List<Store>{
        return repository
            .findAll()
            .parallelStream()
            .map { store ->store?.mapToDomain() }
            .collect(Collectors.toList<Store>())
    }

    override fun searchByName(searchText: String): List<Store> {
        return repository
            .findByNameContainingIgnoreCase(searchText)
            .parallelStream()
            .map { store -> store?.mapToDomain() }
            .collect(Collectors.toList<Store>())
    }

    override fun getById(id: Identity): Optional<Store> {
        return repository
            .findById(id.number)
            .map { store -> store?.mapToDomain() }
    }

    override fun getProductsById(id: Identity): List<Product> {
        return repository
            .findProductsById(id.number)
            .stream()
            .map { product -> product?.mapToDomain() }
            .collect(Collectors.toList<Product>())
    }

    override fun getStoresById(id: Identity): List<Store> {
        return repository
            .findStoresById(id.number)
            .parallelStream()
            .map { store -> store?.mapToDomain() }
            .collect(Collectors.toList<Store>())
    }
}
