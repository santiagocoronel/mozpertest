package com.santiagocoronel.mozpertest.features.employees.data.repository

import com.santiagocoronel.androidbase.data.Response
import com.santiagocoronel.androidbase.exception.GenericException
import com.santiagocoronel.androidbase.exception.ServiceErrorException
import com.santiagocoronel.androidbase.exception.UnAuthorizeException
import com.santiagocoronel.mozpertest.base.BaseUnitTest
import com.santiagocoronel.mozpertest.features.employees.data.repository.network.MozperTestApi
import com.santiagocoronel.mozpertest.features.employees.data.repository.network.dto.response.EmployeeResponse
import com.santiagocoronel.mozpertest.features.employees.data.repository.network.dto.response.EmployeesResultResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock

class EmployeeRepositoryImplTest : BaseUnitTest() {

    @Mock
    private lateinit var api: MozperTestApi

    private lateinit var repository: EmployeeRepositoryImpl

    @Before
    fun setUp() {
        repository = EmployeeRepositoryImpl(api)
    }

    @Test
    fun `test getEmployees then success`(): Unit = runBlocking {

        val mockReturn = EmployeesResultResponse(
            employees = listOf(
                EmployeeResponse(
                    description = "fake_description",
                    firstName = "fake_firstName",
                    id = 0,
                    image = "fake_image",
                    lastName = "fake_lastName",
                    rating = 0.0
                )
            )
        )

        val mockResponse = retrofit2.Response.success(mockReturn)

        BDDMockito.given(
            api.getEmployees()
        ).willReturn(mockResponse)

        repository.getAll().collect { response ->
            when (response) {
                is Response.Success -> {
                    Assert.assertNotNull(response.data)
                }
                is Response.Failure<Exception> -> {

                }
            }
        }
    }

    @Test
    fun `test getEmployees then failure 401`(): Unit = runBlocking {

        val mockReturn = EmployeesResultResponse(
            employees = listOf(
                EmployeeResponse(
                    description = "fake_description",
                    firstName = "fake_firstName",
                    id = 0,
                    image = "fake_image",
                    lastName = "fake_lastName",
                    rating = 0.0
                )
            )
        )

        val mockResponse = retrofit2.Response.success(mockReturn)

        BDDMockito.given(
            api.getEmployees()
        ).willReturn(mockResponse)

        repository.getAll().collect { response ->
            when (response) {
                is Response.Success -> {
                }
                is Response.Failure<Exception> -> {
                    Assert.assertTrue(response.error is UnAuthorizeException)
                }
            }
        }
    }

    @Test
    fun `test getEmployees then failure 404`(): Unit = runBlocking {

        val mockReturn = EmployeesResultResponse(
            employees = listOf(
                EmployeeResponse(
                    description = "fake_description",
                    firstName = "fake_firstName",
                    id = 0,
                    image = "fake_image",
                    lastName = "fake_lastName",
                    rating = 0.0
                )
            )
        )

        val mockResponse = retrofit2.Response.success(mockReturn)

        BDDMockito.given(
            api.getEmployees()
        ).willReturn(mockResponse)

        repository.getAll().collect { response ->
            when (response) {
                is Response.Success -> {
                }
                is Response.Failure<Exception> -> {
                    Assert.assertTrue(response.error is ServiceErrorException)
                }
            }
        }
    }

    @Test
    fun `test getEmployees then failure 500`(): Unit = runBlocking {

        val mockReturn = EmployeesResultResponse(
            employees = listOf(
                EmployeeResponse(
                    description = "fake_description",
                    firstName = "fake_firstName",
                    id = 0,
                    image = "fake_image",
                    lastName = "fake_lastName",
                    rating = 0.0
                )
            )
        )

        val mockResponse = retrofit2.Response.success(mockReturn)

        BDDMockito.given(
            api.getEmployees()
        ).willReturn(mockResponse)

        repository.getAll().collect { response ->
            when (response) {
                is Response.Success -> {
                }
                is Response.Failure<Exception> -> {
                    Assert.assertTrue(response.error is GenericException)
                }
            }
        }
    }

}