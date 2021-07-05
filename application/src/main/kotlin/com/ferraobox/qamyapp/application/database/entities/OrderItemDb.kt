package com.ferraobox.qamyapp.application.database.entities

import javax.persistence.*

@Entity(name = "orderitem")
@Table(name = "orderitem")
class OrderItemDb(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Long?,

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    var order: OrderDb,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    var product: ProductDb,

    @Column(nullable = false)
    var price: Double,

    @Column(nullable = false)
    var quantity: Int,

    @Column(nullable = false)
    var total: Double,
): BaseDbEntity() {
    companion object {
        // TODO: test
        fun newInstance(order: OrderDb, productData: ProductDb, quantity: Int): OrderItemDb {
            return OrderItemDb(
                id = null,
                product = productData,
                price = productData.price,
                quantity = quantity,
                total = (quantity * productData.price),
                order = order
            )
        }
    }
}
