package com.ferraobox.qamyapp.application.presenter.rest.api.store

import com.ferraobox.qamyapp.dto.ProductResponse
import com.ferraobox.qamyapp.dto.StoreResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/Store")
interface StoreResource {
    @GetMapping
    fun all(): CompletableFuture<List<Any?>?>?

    @GetMapping("/search/{text}")
    fun getAllStoresByNameMatching(@PathVariable text: String?): CompletableFuture<List<StoreResponse?>?>?

    @GetMapping("/{id}")
    fun getStoreByIdentity(@PathVariable id: Long): CompletableFuture<StoreResponse?>?

    @GetMapping("/{id}/products")
    fun getProductsBy(@PathVariable id: Long): CompletableFuture<List<ProductResponse?>?>?
}
