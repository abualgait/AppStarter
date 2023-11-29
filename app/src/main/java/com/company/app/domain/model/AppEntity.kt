package com.company.app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.company.app.theme.BabyBlue
import com.company.app.theme.LightGreen
import com.company.app.theme.RedOrange
import com.company.app.theme.RedPink
import com.company.app.theme.Violet

@Entity
data class AppEntity(
    val title: String,
    @PrimaryKey val id: Int? = null
)

class InvalidDataException(message: String) : Exception(message)