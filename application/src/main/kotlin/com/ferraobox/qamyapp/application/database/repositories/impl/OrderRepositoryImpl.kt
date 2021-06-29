package com.ferraobox.qamyapp.application.database.repositories.impl

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.core.mappers.OrderDomainDbMapper
import com.ferraobox.qamyapp.application.core.repositories.IOrderRepository
import com.ferraobox.qamyapp.application.database.entities.OrderDb
import com.ferraobox.qamyapp.application.database.repositories.DbOrderRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class OrderRepositoryImpl(
    private val repository: DbOrderRepository,
    private val orderDomainDbMapper: OrderDomainDbMapper
) : IOrderRepository {

    override fun persist(order: Order): Order {
        val orerDb: OrderDb = orderDomainDbMapper.mapToDb(order)!!
        orerDb.orderItems?.forEach { o -> o.order = orerDb }
        return orderDomainDbMapper.mapToDomain(repository.save(orerDb))!!
    }

    override fun getById(id: Identity?): Optional<Order> {
        return repository
            .findById(id!!.number)
            .map { o -> orderDomainDbMapper.mapToDomain(o) }
    }
}
