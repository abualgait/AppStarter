package com.company.app.domain.repository

import com.company.app.domain.model.AppDomainModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getData(): Flow<List<AppDomainModel>>

    suspend fun getDataById(id: Int): AppDomainModel?

    suspend fun insertData(data: AppDomainModel)

    suspend fun deleteData(data: AppDomainModel)
}