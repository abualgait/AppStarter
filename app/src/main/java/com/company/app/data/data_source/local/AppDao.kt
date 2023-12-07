package com.company.app.data.data_source.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM appentity")
    fun getEntities(): Flow<List<AppEntity>>

    @Query("SELECT * FROM appentity WHERE id = :id")
    suspend fun getEntityById(id: Int): AppEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(entity: AppEntity)

    @Delete
    suspend fun deleteEntity(entity: AppEntity)
}