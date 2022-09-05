package com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.tables.EmployeeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeEntityDao {

    @Query("SELECT id, description, firstName, image, lastName, rating FROM EmployeeEntity")
    fun getAll(): Flow<List<EmployeeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<EmployeeEntity>)

}