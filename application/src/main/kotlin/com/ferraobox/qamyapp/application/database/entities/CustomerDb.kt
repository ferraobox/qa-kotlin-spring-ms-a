package com.ferraobox.qamyapp.application.database.entities

import javax.persistence.*

@Table(name = "customer")
@Entity(name = "customer")
class CustomerDb(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long?,

    @Column(nullable = false)
    var name: String,

    @Column(unique = true, nullable = false)
    var email: String,

    @Column(nullable = false)
    var address: String,

    @Column(nullable = false)
    var password: String,
) : BaseDbEntity() {

    companion object {
        // TODO: test
        fun newInstance(name: String, email: String, address: String, password: String): CustomerDb {
            return CustomerDb(id = null, name = name, email = email, address = address, password = password)
        }
    }
}