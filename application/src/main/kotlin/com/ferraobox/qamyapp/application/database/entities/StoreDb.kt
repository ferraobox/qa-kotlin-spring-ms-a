package com.ferraobox.qamyapp.application.database.entities

import net.bytebuddy.implementation.bind.annotation.Default
import javax.persistence.*

@Table(name = "store")
@Entity(name = "store")
class StoreDb(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Long?,

    @Column(unique = true, nullable = false)
    var name: String?,

    @Column(nullable = false)
    var address: String?,

    @javax.persistence.ManyToOne
    @JoinColumn(name = "cousine_id", nullable = false)
    var cousine: CousineDb?,

    @OneToMany(mappedBy = "store")
    @Default
    var products: Set<ProductDb> = HashSet()
) : BaseDbEntity() {
    constructor() : this(null, null, null,null,HashSet()) {
    }
}