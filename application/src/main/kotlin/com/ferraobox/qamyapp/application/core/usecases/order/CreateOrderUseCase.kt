package com.ferraobox.qamyapp.application.core.usecases.order

import com.ferraobox.qamyapp.application.core.domain.*
import com.ferraobox.qamyapp.application.core.repositories.IOrderRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import com.ferraobox.qamyapp.application.core.usecases.product.GetProductsByStoreAndProductsIdUseCase
import org.springframework.stereotype.Component
import java.time.Instant


@Component
open class CreateOrderUseCase(
    private val getProductsByStoreAndProductsIdUseCase: GetProductsByStoreAndProductsIdUseCase,
    private val orderRepository: IOrderRepository
) : UseCase<CreateOrderUseCase.InputValues, CreateOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val order: Order = createOrder(input)
        return OutputValues(orderRepository.persist(order))
    }

    private fun createOrder(input: InputValues): Order {
        val orderItems: List<OrderItem> = createOrderItems(input)
        val order = Order(
            id = Identity(),
            customer = input.customer,
            store = getFirstProductStore(orderItems),
            orderItems = orderItems,
            status = Status.OPEN,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            total = null
        )
        order.total = order.calculateTotal(orderItems)
        return order

    }

    private fun getFirstProductStore(orderItems: List<OrderItem>): Store {
        return orderItems[0].product.store
    }

    private fun createOrderItems(input: InputValues): List<OrderItem> {
        val productMap: Map<Identity, Product> = getProducts(input)
        return input.orderItems.map { inputItem -> createOrderItem(inputItem, productMap) }.toList()
    }

    private fun createOrderItem(inputItem: InputItem, productMap: Map<Identity, Product>): OrderItem {
        val product: Product = productMap[inputItem.id]!!
        return OrderItem(
            id = Identity(),
            product = product,
            quantity = inputItem.quantity,
            total = (inputItem.quantity * product.price)
        )
    }

    private fun getProducts(input: InputValues): Map<Identity, Product> {
        val inputValues: GetProductsByStoreAndProductsIdUseCase.InputValues =
            GetProductsByStoreAndProductsIdUseCase.InputValues(
                storeId = input.storeId,
                productsId = createListOfProductsId(input.orderItems)
            )
        return getProductsByStoreAndProductsIdUseCase!!.execute(inputValues).products!!.associateBy { it.id }


    }

    private fun createListOfProductsId(inputItems: List<InputItem>): List<Identity> {
        return inputItems.map { it.id!! }.toList<Identity>()
    }

    data class InputValues(
        var customer: Customer?,
        var storeId: Identity,
        var orderItems: List<InputItem>
    ) : UseCase.InputValues


    data class OutputValues(
        val order: Order?
    ) : UseCase.OutputValues


    data class InputItem(
        var id: Identity,
        var quantity: Int = 0
    )
}