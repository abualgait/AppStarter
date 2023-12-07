package com.company.app.domain.use_case

import com.company.app.domain.model.AppDomainModel
import com.company.app.domain.repository.AppRepository

class DeleteData(
    private val repository: AppRepository
) {

    suspend operator fun invoke(data: AppDomainModel) {
        repository.deleteData(data)
    }
}