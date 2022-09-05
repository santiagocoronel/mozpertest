package com.santiagocoronel.mozpertest.features.employees.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.santiagocoronel.androidbase.Event
import com.santiagocoronel.androidbase.presenter.BaseViewModel
import com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.tables.EmployeeEntity
import com.santiagocoronel.mozpertest.features.employees.domain.GetEmployeesUseCase
import com.santiagocoronel.mozpertest.features.employees.domain.model.DataSourceType
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class EmployeeViewModel(private val getEmployeesUseCase: GetEmployeesUseCase) : BaseViewModel() {

    private val _employees = MutableLiveData<List<EmployeeEntity>>()
    val employeesLD: LiveData<List<EmployeeEntity>> = _employees

    private val _offlineMode = MutableLiveData<Event<Unit>>()
    val offlineModeLD: LiveData<Event<Unit>> = _offlineMode

    fun getEmployees() {
        notifyShowLoading()
        viewModelScope.launch {
            executeSimpleUseCase(getEmployeesUseCase).single().catch {
                notifyRemoveLoading()
                notifyError(Exception(it))
            }.collect {
                notifyRemoveLoading()
                _employees.value = it.employeeList
                if (it.datasource == DataSourceType.LOCAL) {
                    _offlineMode.value = Event(Unit)
                }
            }
        }
    }
}