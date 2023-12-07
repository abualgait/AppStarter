package com.company.app.domain.use_case

import com.company.app.domain.model.AppDomainModel
import com.company.app.domain.repository.AppRepository

class GetData(
    private val repository: AppRepository
) {

    suspend operator fun invoke(id: Int): AppDomainModel? {
        return repository.getDataById(id)
    }
}