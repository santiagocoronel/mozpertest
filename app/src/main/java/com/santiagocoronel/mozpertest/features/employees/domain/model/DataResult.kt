package com.santiagocoronel.mozpertest.features.employees.domain.model

import com.santiagocoronel.mozpertest.features.employees.data.repository.local.db.tables.EmployeeEntity

data class DataResult(
    val employeeList: List<EmployeeEntity>,
    val datasource: DataSourceType
)
