package com.company.app.domain.repository

import com.company.app.domain.model.AppEntity
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getData(): Flow<List<AppEntity>>

    suspend fun getDataById(id: Int): AppEntity?

    suspend fun insertData(data: AppEntity)

    suspend fun deleteData(data: AppEntity)
}