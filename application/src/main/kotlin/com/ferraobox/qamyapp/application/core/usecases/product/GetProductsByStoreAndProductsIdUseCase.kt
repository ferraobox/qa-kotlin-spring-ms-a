package com.ferraobox.qamyapp.application.core.usecases.product

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.NotFoundException
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.core.repositories.IProductRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component
import java.util.stream.Collectors


@Component
class GetProductsByStoreAndProductsIdUseCase(private val repository: IProductRepository) :
    UseCase<GetProductsByStoreAndProductsIdUseCase.InputValues, GetProductsByStoreAndProductsIdUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val distinctProductsId: List<Identity>? = input.productsId
        val foundProducts: List<Product>? =
            repository.searchProductsByStoreAndProductsId(input.storeId, distinctProductsId)
        if (foundProducts != null && distinctProductsId != null) {
            throwIfAnyProductIsNotFound(distinctProductsId, foundProducts)
        }
        return OutputValues(foundProducts)
    }

    private fun throwIfAnyProductIsNotFound(
        distinctProductsId: List<Identity>,
        foundProducts: List<Product>
    ) {
        if (distinctProductsId.size != foundProducts.size) {
            val message = createErrorMessage(distinctProductsId, foundProducts)
            throw NotFoundException(message)
        }
    }

    private fun createErrorMessage(distinctProductsId: List<Identity>, foundProducts: List<Product>): String {
        val missingProductsId = getMissingProductsId(distinctProductsId, foundProducts)
        return String.format("Product(s) %s not found", java.lang.String.join(", ", missingProductsId))
    }

    private fun getMissingProductsId(distinctProductsId: List<Identity>, foundProducts: List<Product>): List<String> {
        val distinctProductsIdSet = createDistinctProductsIdSet(distinctProductsId)
        val foundProductsId = createFoundProductsIdSet(foundProducts)
        distinctProductsIdSet.removeAll(foundProductsId)
        return distinctProductsIdSet
            .stream()
            .map { obj: Long -> obj.toString() }
            .collect(Collectors.toList())
    }

    private fun createFoundProductsIdSet(foundProducts: List<Product>): Set<Long> {
        return foundProducts.map { product -> product.id }.map { identity -> identity.number }.toSet<Long>()
    }

    private fun createDistinctProductsIdSet(distinctProductsId: List<Identity>): MutableSet<Long> {
        return distinctProductsId.map { identity -> identity.number }.toMutableSet<Long>()
    }

    private fun distinctIds(identities: List<Identity>): List<Identity> {
        return identities.distinct().toList<Identity>()
    }

    data class InputValues(
        var storeId: Identity?,
        var productsId: List<Identity>?
    ) : UseCase.InputValues

    data class OutputValues(
        var products: List<Product>?
    ) : UseCase.OutputValues
}