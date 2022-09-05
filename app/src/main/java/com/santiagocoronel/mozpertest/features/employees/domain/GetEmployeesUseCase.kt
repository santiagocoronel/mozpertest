package com.santiagocoronel.mozpertest.features.employees.domain

import com.santiagocoronel.androidbase.data.Response
import com.santiagocoronel.androidbase.domain.BaseUseCase
import com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.MozperDataBase
import com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.tables.EmployeeEntity
import com.santiagocoronel.mozpertest.features.employees.domain.model.DataResult
import com.santiagocoronel.mozpertest.features.employees.domain.model.DataSourceType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

class GetEmployeesUseCase(
    private val repository: EmployeeRepository,
    private val database: MozperDataBase
) : BaseUseCase<Flow<DataResult>>() {

    override suspend fun execute(): Flow<DataResult> = flow {
        repository.getAll().collect { response ->
            when (response) {
                is Response.Success -> {
                    database.employeeDao().insert(response.data)
                    emit(DataResult(response.data, DataSourceType.NETWORK))
                }
                is Response.Failure<Exception> -> {
                    if (response.error is UnknownHostException) {
                        database.employeeDao().getAll().collect { localData ->
                            emit(DataResult(localData, DataSourceType.LOCAL))
                        }
                    } else {
                        throw response.error
                    }
                }
            }
        }
    }

}