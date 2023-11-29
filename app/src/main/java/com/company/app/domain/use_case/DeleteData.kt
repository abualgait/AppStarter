package com.company.app.domain.use_case

import com.company.app.domain.model.AppEntity
import com.company.app.domain.repository.AppRepository

class DeleteData(
    private val repository: AppRepository
) {

    suspend operator fun invoke(data: AppEntity) {
        repository.deleteData(data)
    }
}