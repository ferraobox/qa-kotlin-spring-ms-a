package com.ferraobox.qamyapp.application.presenter.rest.api.cousine

import com.ferraobox.qamyapp.dto.CousineResponse
import com.ferraobox.qamyapp.dto.StoreResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture


@RestController
@RequestMapping("/cousine")
interface CousineResource {
    @GetMapping("/{id}/store")
    fun getStoresByCousineId(@PathVariable id: Long): CompletableFuture<List<StoreResponse?>?>

    @GetMapping
    fun allCousines(): CompletableFuture<List<Any?>?>

    @GetMapping("/search/{text}")
    fun getAllCousinesByNameMatching(@PathVariable text: String): CompletableFuture<List<CousineResponse?>?>
}