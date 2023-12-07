package com.company.app.domain.use_case

import com.company.app.domain.model.AppDomainModel
import com.company.app.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetListOfData(
    private val repository: AppRepository
) {
    operator fun invoke(): Flow<List<AppDomainModel>> {
        return repository.getData()
    }
}