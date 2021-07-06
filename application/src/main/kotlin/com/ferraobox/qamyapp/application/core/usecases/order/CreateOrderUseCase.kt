package com.ferraobox.qamyapp.application.core.usecases.order

import com.ferraobox.qamyapp.application.core.domain.*
import com.ferraobox.qamyapp.application.core.repositories.IOrderRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import com.ferraobox.qamyapp.application.core.usecases.product.GetProductsByStoreAndProductsIdUseCase
import com.ferraobox.qamyapp.application.core.usecases.store.GetStoreUseCase
import com.ferraobox.qamyapp.dto.OrderRequestItem
import org.springframework.stereotype.Component
import java.time.Instant

@Component
open class CreateOrderUseCase(
    private val getProductsByStoreAndProductsIdUseCase: GetProductsByStoreAndProductsIdUseCase,
    private val getStoreUseCase: GetStoreUseCase,
    private val orderRepository: IOrderRepository
) : UseCase<CreateOrderUseCase.InputValues, CreateOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val order: Order = createOrder(input)
        return OutputValues(orderRepository.persist(order))
    }

    private fun createOrder(input: InputValues): Order {
        val order = Order(
            id = Identity(),
            customer = input.customer,
            store = getStoreUseCase.execute(GetStoreUseCase.InputValues(input.storeId)).store!!,
            orderItems = ArrayList(),
            status = Status.OPEN,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            total = 0.0
        )
        val orderItems: List<OrderItem> = createOrderItems(input, order)
        order.orderItems = orderItems
        order.total = order.calculateTotal(orderItems)
        return order

    }

    private fun createOrderItems(input: InputValues, order: Order): List<OrderItem> {
        val productMap: Map<Identity, Product> = getProducts(input)
        return input.orderItems.map { inputItem -> createOrderItem(inputItem, productMap, order) }.toList()
    }

    private fun createOrderItem(inputItem: OrderRequestItem, productMap: Map<Identity, Product>, order: Order): OrderItem {
        val productId = Identity(inputItem.id)
        val product: Product = productMap[productId]!!
        return OrderItem(
            id = Identity(),
            product = product,
            order = order,
            quantity = inputItem.quantity,
            total = (inputItem.quantity * product.price),
            price = product.price
        )
    }

    private fun getProducts(input: InputValues): Map<Identity, Product> {
        val inputValues: GetProductsByStoreAndProductsIdUseCase.InputValues =
            GetProductsByStoreAndProductsIdUseCase.InputValues(
                storeId = input.storeId,
                productsId = createListOfProductsId(input.orderItems)
            )
        return getProductsByStoreAndProductsIdUseCase.execute(inputValues).products.associateBy { it.id }
    }

    private fun createListOfProductsId(inputItems: List<OrderRequestItem>): List<Identity> {
        return inputItems.map { Identity(it.id) }.toList<Identity>()
    }

    data class InputValues(
        var customer: Customer,
        var storeId: Identity,
        var orderItems: List<OrderRequestItem>
    ) : UseCase.InputValues

    data class OutputValues(
        val order: Order?
    ) : UseCase.OutputValues

    data class InputItem(
        var id: Identity,
        var quantity: Int = 0
    )
}