package com.santiagocoronel.mozpertest.features.home.data.repository.network.dto.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class EmployeesResultResponse(
    @SerializedName("employees")
    val employees: List<EmployeeResponse>
)