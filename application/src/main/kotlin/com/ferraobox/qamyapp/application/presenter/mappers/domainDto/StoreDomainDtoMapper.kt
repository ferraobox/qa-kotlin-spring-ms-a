package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Store
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.dto.StoreResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [IdConverter::class, Identity::class],
    uses = []
)
interface StoreDomainDtoMapper : BaseDomainDtoMapper<Store?, StoreResponse?> {

    @Mappings(
        Mapping(expression = "java(IdConverter.INSTANCE.convertId(store.getId()))", target = "id"),
        Mapping(source = "name", target = "name"),
        Mapping(source = "address", target = "address")
    )
    override fun mapToDto(store: Store?): StoreResponse?
    override fun mapToDto(store: List<Store?>, list: Boolean): List<StoreResponse?>

    @Mappings(
        Mapping(expression = "java(new Identity(store.getId()))", target = "id"),
        Mapping(source = "name", target = "name"),
        Mapping(source = "address", target = "address")
    )
    override fun mapToDomain(store: StoreResponse?): Store?
    override fun mapToDomain(store: List<StoreResponse?>, list: Boolean): List<Store?>
}
