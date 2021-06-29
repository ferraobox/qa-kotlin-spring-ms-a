package com.ferraobox.qamyapp.application.database.entities

import javax.persistence.*

@Table(name = "customer")
@Entity(name = "customer")
class CustomerDb (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Long?,

    @Column(nullable = false)
    var name: String?,

    @Column(unique = true, nullable = false)
    var email: String?,

    @Column(nullable = false)
    var address: String?,

    @Column(nullable = false)
    var password: String?,
): BaseDbEntity(){
    constructor() : this(null,null,null ,null,null) {
    }
}