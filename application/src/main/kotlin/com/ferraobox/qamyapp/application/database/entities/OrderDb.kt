package com.ferraobox.qamyapp.application.database.entities

import com.ferraobox.qamyapp.application.core.domain.Status
import net.bytebuddy.implementation.bind.annotation.Default
import java.time.Instant
import java.util.function.Consumer
import javax.persistence.*

@Entity(name = "order")
@Table(name = "orders")
class OrderDb(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Long?,

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    var customer: CustomerDb?,

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    var store: StoreDb?,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    @Default
    var orderItems: MutableSet<OrderItemDb>? = HashSet<OrderItemDb>(),

    @Column(nullable = false)
    var total: Double?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: Status?,

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant?,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant?,
) : BaseDbEntity() {
    constructor() : this(null, null, null, HashSet<OrderItemDb>(), null, null, null, null)

    // TODO: test
    private fun addOrderItem(orderItem: OrderItemDb) {
        if (orderItems == null) {
            orderItems = HashSet<OrderItemDb>()
        }
        orderItem.order = this
        orderItems!!.add(orderItem)
        calculateTotal()
    }

    private fun calculateTotal() {
        total = orderItems?.sumByDouble { it.total!! }
    }

    companion object {
        // TODO: test
        fun newInstance(
            customer: CustomerDb?,
            store: StoreDb?,
            orderItems: List<OrderItemDb?>
        ): OrderDb {
            val order = OrderDb(
                id = null,
                customer = customer,
                store = store,
                orderItems = null,
                total = 0.0,
                status = Status.OPEN,
                createdAt = Instant.now(),
                updatedAt = Instant.now()
            )
            orderItems.forEach { orderItem ->
                if (orderItem != null) {
                    order.addOrderItem(
                        orderItem
                    )
                }
            }
            return order
        }
    }
}