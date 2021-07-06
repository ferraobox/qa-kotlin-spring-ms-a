package com.ferraobox.qamyapp.application.database.entities

import net.bytebuddy.implementation.bind.annotation.Default
import javax.persistence.*


@Entity(name = "cousine")
@Table(name = "cousine")
class CousineDb(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long?,

    @Column(unique = true, nullable = false)
    var name: String,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cousine", cascade = [CascadeType.ALL])
    @Default
    var stores: MutableSet<StoreDb> = HashSet()

) : BaseDbEntity() {
    // TODO: test
    fun addStore(store: StoreDb) {
        stores = HashSet()
        store.cousine = this
        stores.add(store)
    }

    companion object {
        // TODO: test
        fun newInstance(name: String): CousineDb {
            return CousineDb(id = null, name = name, stores = HashSet())
        }
    }
}
