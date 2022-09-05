package com.santiagocoronel.mozpertest.features.home.domain

import com.santiagocoronel.androidbase.data.Response
import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.tables.EmployeeEntity
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

interface EmployeeRepository {

    @Throws(Exception::class)
    suspend fun getAll(): Flow<Response<List<EmployeeEntity>>>

}