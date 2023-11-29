package com.company.app.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.app.domain.model.AppEntity

@Database(
    entities = [AppEntity::class],
    version = 1,
    exportSchema = false // edit this before production, create migration schema
)
abstract class AppDatabase : RoomDatabase() {

    abstract val appDao: AppDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}