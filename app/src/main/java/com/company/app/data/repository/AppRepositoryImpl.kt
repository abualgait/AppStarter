package com.company.app.data.repository

import com.company.app.data.data_source.local.AppDao
import com.company.app.data.data_source.remote.RetrofitService
import com.company.app.domain.model.AppEntity
import com.company.app.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(
    private val dao: AppDao,
    private val retrofitService: RetrofitService
) : AppRepository {

    override fun getData(): Flow<List<AppEntity>> = dao.getEntities()

    override suspend fun getDataById(id: Int): AppEntity? {
        return dao.getEntityById(id)
    }

    override suspend fun insertData(data: AppEntity) {
        dao.insertEntity(data)
    }

    override suspend fun deleteData(data: AppEntity) {
        dao.deleteEntity(data)
    }
}