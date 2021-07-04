package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Store
import com.ferraobox.qamyapp.application.core.mappers.CousineDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.CousineDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.mappers.ProductDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.ProductDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.database.entities.StoreDb
import java.util.*
import kotlin.collections.ArrayList


object StoreDomainDbMapper {

    fun Store.mapToDb(): StoreDb {
        return StoreDb(id=this.id.number ,name = this.name, address = this.address, cousine = this.cousine.mapToDb(), products = this.products.mapToDb())
    }

    fun List<Store>.mapToDb(): MutableSet<StoreDb> {
        val storeDbList: MutableSet<StoreDb> = HashSet()
        forEach { store ->
            storeDbList.add(store.mapToDb())
        }
        return storeDbList
    }

    fun StoreDb.mapToDomain(): Store {
        return Store(id = Identity(this.id!!),name = this.name, address=this.address,cousine = this.cousine.mapToDomain(), products = this.products.mapToDomain())
    }

    fun MutableSet<StoreDb>.mapToDomain(): List<Store> {
        val storeList = ArrayList<Store>()
        forEach { store ->
            storeList.add(store.mapToDomain())
        }
        return storeList
    }
}