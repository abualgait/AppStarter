package com.company.app.data.data_source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppEntity(
    val title: String,
    @PrimaryKey val id: Int? = null
)

class InvalidDataException(message: String) : Exception(message)