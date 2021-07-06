package com.ferraobox.qamyapp.application.core.domain

data class OrderItem(
    override var id: Identity,
    var quantity: Int,
    var product: Product,
    var price: Double,
    var total: Double
) : BaseDomainEntity()