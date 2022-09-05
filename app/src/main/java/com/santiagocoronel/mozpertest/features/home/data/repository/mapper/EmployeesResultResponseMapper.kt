package com.santiagocoronel.mozpertest.features.home.data.repository.mapper

import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.tables.EmployeeEntity
import com.santiagocoronel.mozpertest.features.home.data.repository.network.dto.response.EmployeeResponse

object EmployeesResultResponseMapper {

    fun toEmployeeEntity(enter: EmployeeResponse): EmployeeEntity {
        return EmployeeEntity(
            id = enter.id,
            description = enter.description,
            firstName = enter.firstName,
            image = enter.image,
            lastName = enter.lastName,
            rating = enter.rating
        )
    }
}