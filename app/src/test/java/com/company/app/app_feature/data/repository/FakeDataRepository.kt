package com.company.app.app_feature.data.repository

import com.company.app.data.data_source.remote.RetrofitService
import com.company.app.domain.model.AppEntity
import com.company.app.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDataRepository(
    private val retrofitService: RetrofitService
) : AppRepository {

    private val data = mutableListOf<AppEntity>()

    override fun getData(): Flow<List<AppEntity>> = flowOf(data)

    override suspend fun getDataById(id: Int): AppEntity? {
        return data.find { it.id == id }
    }

    override suspend fun insertData(data: AppEntity) {
        this.data.add(data)
    }

    override suspend fun deleteData(data: AppEntity) {
        this.data.remove(data)
    }
}