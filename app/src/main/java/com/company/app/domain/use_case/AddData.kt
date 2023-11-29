package com.company.app.domain.use_case

import com.company.app.domain.model.InvalidDataException
import com.company.app.domain.model.AppEntity
import com.company.app.domain.repository.AppRepository

class AddData(
    private val repository: AppRepository
) {

    @Throws(InvalidDataException::class)
    suspend operator fun invoke(data: AppEntity) {
        if (data.title.isBlank()) {
            throw InvalidDataException("The title of the entity can't be empty.")
        }
        repository.insertData(data)
    }
}