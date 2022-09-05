package com.santiagocoronel.mozpertest.features.employees.data.repository.mapper

import com.santiagocoronel.mozpertest.base.BaseUnitTest
import com.santiagocoronel.mozpertest.features.employees.data.repository.network.dto.response.EmployeeResponse
import org.junit.Assert
import org.junit.Test

class EmployeesResultResponseMapperTest : BaseUnitTest() {

    @Test
    fun`test map EmployeeResponse to toEmployeeEntity`(){
        //given
        val from = EmployeeResponse(
            description = "fake_description",
            firstName = "fake_firstName",
            id = 0,
            image = "fake_image",
            lastName = "fake_lastName",
            rating = 0.0
        )
        //when
        val mapped = EmployeesResultResponseMapper.toEmployeeEntity(from)
        //then
        Assert.assertEquals(from.description, mapped.description)
        Assert.assertEquals(from.firstName, mapped.firstName)
        Assert.assertEquals(from.id, mapped.id)
        Assert.assertEquals(from.image, mapped.image)
        Assert.assertEquals(from.lastName, mapped.lastName)
        Assert.assertEquals(from.rating, mapped.rating, 0.0)
    }
}