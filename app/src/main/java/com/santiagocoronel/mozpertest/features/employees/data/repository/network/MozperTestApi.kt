package com.santiagocoronel.mozpertest.features.employees.data.repository.network

import com.santiagocoronel.mozpertest.features.employees.data.repository.network.dto.response.EmployeesResultResponse
import retrofit2.Response
import retrofit2.http.GET

interface MozperTestApi {

    @GET(".")
    suspend fun getEmployees(): Response<EmployeesResultResponse>

}