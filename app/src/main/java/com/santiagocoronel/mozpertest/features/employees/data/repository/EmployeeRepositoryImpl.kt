package com.santiagocoronel.mozpertest.features.employees.data.repository

import com.santiagocoronel.androidbase.data.BaseRepository
import com.santiagocoronel.androidbase.data.Response
import com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.tables.EmployeeEntity
import com.santiagocoronel.mozpertest.features.employees.data.repository.mapper.EmployeesResultResponseMapper
import com.santiagocoronel.mozpertest.features.employees.data.repository.network.MozperTestApi
import com.santiagocoronel.mozpertest.features.employees.domain.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class EmployeeRepositoryImpl(private val api: MozperTestApi) : BaseRepository() , EmployeeRepository {

    @Throws(java.lang.Exception::class)
    override suspend fun getAll(): Flow<Response<List<EmployeeEntity>>> = flow {
        val response = api.getEmployees()

        try {
            processResponse(response).employees.let { list ->
                val mappedList = list.map { EmployeesResultResponseMapper.toEmployeeEntity(it) }
                emit(Response.Success(mappedList))
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Response.Failure(exception))
        }
    }.catch {
        emit(handleCatch(it))
    }

}