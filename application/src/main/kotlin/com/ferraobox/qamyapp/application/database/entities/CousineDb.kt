package com.ferraobox.qamyapp.application.database.entities

import net.bytebuddy.implementation.bind.annotation.Default
import javax.persistence.*


@Entity(name = "cousine")
@Table(name = "cousine")
class CousineDb(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Long?,

    @Column(unique = true, nullable = false)
    var name: String?,

    @OneToMany(mappedBy = "cousine", cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @Default
    var stores: MutableSet<StoreDb>? = HashSet()

) : BaseDbEntity() {

    constructor() : this(null, null, null)

    // TODO: test
    fun addStore(store: StoreDb) {
        if (stores == null) {
            stores = HashSet()
        }
        store.cousine = this
        stores!!.add(store)
    }

    companion object {
        // TODO: test
        fun newInstance(name: String?): CousineDb {
            return CousineDb(id = null, name = name, stores = null)
        }
    }
}
