package com.company.app.data.data_source.local


import com.company.app.domain.DomainMapper
import com.company.app.domain.model.AppDomainModel


class AppEntityMapper : DomainMapper<AppEntity, AppDomainModel> {
    override fun mapToDomainModel(model: AppEntity): AppDomainModel {
        return AppDomainModel(
            id = model.id,
            title = model.title

        )
    }

    override fun mapFromDomainModel(domainModel: AppDomainModel): AppEntity {
        return AppEntity(
            id = domainModel.id,
            title = domainModel.title
        )
    }

    fun fromEntityList(initial: List<AppEntity>): List<AppDomainModel> {
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<AppDomainModel>): List<AppEntity> {
        return initial.map { mapFromDomainModel(it) }
    }


}






