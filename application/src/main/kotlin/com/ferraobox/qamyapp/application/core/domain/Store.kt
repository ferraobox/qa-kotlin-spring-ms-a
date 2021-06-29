package com.ferraobox.qamyapp.application.core.domain

data class Store(
    override var id: Identity,
    var name: String,
    var address: String,
    var cousine: Cousine,
) : BaseDomainEntity()