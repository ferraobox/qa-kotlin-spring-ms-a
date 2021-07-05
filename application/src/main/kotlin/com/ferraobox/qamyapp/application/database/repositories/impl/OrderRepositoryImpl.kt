package com.ferraobox.qamyapp.application.database.repositories.impl

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.core.mappers.OrderDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.OrderDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.repositories.IOrderRepository
import com.ferraobox.qamyapp.application.database.entities.OrderDb
import com.ferraobox.qamyapp.application.database.repositories.DbOrderRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class OrderRepositoryImpl(
    private val repository: DbOrderRepository,
) : IOrderRepository {

    override fun persist(order: Order): Order {
        val orerDb: OrderDb = order.mapToDb()
        return repository.save(orerDb).mapToDomain()
    }

    override fun getById(id: Identity): Optional<Order> {
        return repository
            .findById(id.number)
            .map { o -> o?.mapToDomain() }
    }
}
