package com.company.app.domain.use_case

import com.company.app.domain.model.AppEntity
import com.company.app.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetListOfData(
    private val repository: AppRepository
) {
    operator fun invoke(): Flow<List<AppEntity>> {
        return repository.getData()
    }
}