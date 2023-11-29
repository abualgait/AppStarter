package com.company.app.domain.use_case

import com.company.app.domain.model.AppEntity
import com.company.app.domain.repository.AppRepository

class GetData(
    private val repository: AppRepository
) {

    suspend operator fun invoke(id: Int): AppEntity? {
        return repository.getDataById(id)
    }
}