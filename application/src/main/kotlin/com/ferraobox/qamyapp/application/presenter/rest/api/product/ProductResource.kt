package com.ferraobox.qamyapp.application.presenter.rest.api.product

import com.ferraobox.qamyapp.dto.ProductResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture


@RestController
@RequestMapping("/Product")
interface ProductResource {
    @GetMapping
    fun allProducts(): CompletableFuture<List<Any?>?>

    @GetMapping("/{id}")
    fun getByIdentity(@PathVariable id: Long): CompletableFuture<ProductResponse?>?

    @GetMapping("/search/{text}")
    fun getByMatchingName(@PathVariable text: String): CompletableFuture<List<ProductResponse?>?>?
}