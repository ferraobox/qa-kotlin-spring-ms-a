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
    var name: String,

    @Column(nullable = false)
    var address: String,

    @ManyToOne
    @JoinColumn(name = "cousine_id", nullable = false)
    var cousine: CousineDb,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "store", cascade = [CascadeType.ALL])
    @Default
    var products: MutableSet<ProductDb> = HashSet()
) : BaseDbEntity() {

    companion object {
        // TODO: test
        fun newInstance(name: String,address: String,cousine: CousineDb): StoreDb {
            return StoreDb(id = null, name = name, address=address, cousine=cousine, products = HashSet())
        }
    }
}