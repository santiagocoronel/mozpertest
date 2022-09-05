package com.santiagocoronel.mozpertest.features.home.domain

import androidx.lifecycle.Observer
import com.santiagocoronel.androidbase.data.Response
import com.santiagocoronel.androidbase.domain.BaseUseCase
import com.santiagocoronel.androidbase.exception.NoInternetException
import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.MozperDataBase
import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.tables.EmployeeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class GetEmployeesUseCase(
    private val repository: EmployeeRepository,
    private val database: MozperDataBase
) : BaseUseCase<Flow<List<EmployeeEntity>>>() {

    override suspend fun execute(): Flow<List<EmployeeEntity>> = flow {
        repository.getAll().collect { response ->
            when (response) {
                is Response.Success -> {
                    database.employeeDao().insert(response.data)
                    emit(response.data)
                }
                is Response.Failure<Exception> -> {
                    if (response.error is UnknownHostException) {
                        database.employeeDao().getAll().collect { localData ->
                            emit(localData)
                        }
                    } else {
                        throw response.error
                    }
                }
            }
        }
    }

}