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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class GetEmployeesUseCase(
    private val repository: EmployeeRepository,
    private val database: MozperDataBase
) : BaseUseCase<Flow<List<EmployeeEntity>>>() {

    override suspend fun execute(): Flow<List<EmployeeEntity>> = flow {

        repository.getAll().collect { response ->
            when (response) {
                is Response.Success -> {
                    emit(response.data)
                    //need refresh local data.
                }
                is Response.Failure<Exception> -> {
                    if (response.error is NoInternetException) {
                        //return local data
                        GlobalScope.launch(Dispatchers.IO) {
                            database.employeeDao().getAll().value?.let { localData ->
                                emit(localData)
                            }
                        }
                    } else {

                    }
                }
            }
        }
    }

}