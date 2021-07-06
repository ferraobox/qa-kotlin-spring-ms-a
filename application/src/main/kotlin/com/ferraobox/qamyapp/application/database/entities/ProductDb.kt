package com.ferraobox.qamyapp.application.database.entities

import javax.persistence.*

@Table(name = "product")
@Entity(name = "product")
class ProductDb(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Long?,

    @Column(unique = true, nullable = false)
    var name: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var price: Double,

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    var store: StoreDb,
) : BaseDbEntity() {

    companion object {
        // TODO: test
        fun newInstance(name: String, description: String, price: Double ,store: StoreDb,): ProductDb {
            return ProductDb(id = null, name = name, description = description, price = price, store = store)
        }
    }

}