package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.StoreDomainDtoMapper.mapToDomain
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.StoreDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.dto.ProductResponse
import org.springframework.stereotype.Component

@Component
object ProductDomainDtoMapper {

    fun Product.mapToDto(): ProductResponse {
        return ProductResponse(
            id = this.id.number,
            name = this.name,
            description = this.description,
            price = this.price,
            store = this.store.mapToDto()
        )
    }

    fun List<Product>.mapToDto(): List<ProductResponse> {
        val productListResponse = ArrayList<ProductResponse>()
        forEach { product -> productListResponse.add(product.mapToDto()) }
        return productListResponse
    }


    fun ProductResponse.mapToDomain(): Product {
        return Product(
            id = Identity(this.id),
            name = this.name,
            description = this.description,
            price = this.price,
            store = this.store.mapToDomain()
        )
    }

    fun List<ProductResponse>.mapToDomain(): List<Product> {
        val productList = ArrayList<Product>()
        forEach { product -> productList.add(product.mapToDomain()) }
        return productList
    }
}