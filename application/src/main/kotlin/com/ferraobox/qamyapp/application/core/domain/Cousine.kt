package com.ferraobox.qamyapp.application.core.domain

data class Cousine(
    override var id: Identity,
    var name: String
) : BaseDomainEntity()