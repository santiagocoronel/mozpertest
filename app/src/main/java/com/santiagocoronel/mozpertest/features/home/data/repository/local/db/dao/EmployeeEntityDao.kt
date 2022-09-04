package com.santiagocoronel.mozpertest.features.home.data.repository.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.tables.EmployeeEntity

@Dao
interface EmployeeEntityDao {

    @Query("SELECT id, description, firstName, image, lastName, rating FROM EmployeeEntity")
    suspend fun getAll(): LiveData<List<EmployeeEntity>>


}