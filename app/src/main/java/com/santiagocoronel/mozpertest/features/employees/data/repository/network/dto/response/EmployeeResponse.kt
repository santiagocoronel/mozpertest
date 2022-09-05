package com.santiagocoronel.mozpertest.features.employees.data.repository.network.dto.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class EmployeeResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("rating")
    val rating: Double
)