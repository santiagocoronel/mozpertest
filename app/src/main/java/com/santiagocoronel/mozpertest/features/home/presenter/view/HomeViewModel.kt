package com.santiagocoronel.mozpertest.features.home.presenter.view

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.santiagocoronel.androidbase.presenter.BaseViewModel
import com.santiagocoronel.mozpertest.features.home.domain.GetEmployeesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class HomeViewModel(private val getEmployeesUseCase: GetEmployeesUseCase) : BaseViewModel() {


    fun getEmployees() {
        viewModelScope.launch {
            executeSimpleUseCase(getEmployeesUseCase).single().collect {
                Log.d("HomeViewModel", "getEmployees: ${it}")
            }
        }
    }
}