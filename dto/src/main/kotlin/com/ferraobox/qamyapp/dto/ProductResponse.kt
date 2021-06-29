package com.ferraobox.qamyapp.dto

data class ProductResponse (
    private val id: Long?,
    private val name: String?,
    private val description: String?,
    private val price: Double?,
    private val storeId: Long?,
)