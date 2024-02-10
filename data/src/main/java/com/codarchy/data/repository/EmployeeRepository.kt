package com.codarchy.data.repository

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.errorhandling.safeApiCall
import com.codarchy.data.mapping.toEmployeeDetail
import com.codarchy.data.mapping.toEmployeeResult
import com.codarchy.data.model.EmployeeResults
import com.codarchy.data.model.PersonAdvancedDetails
import com.codarchy.data.model.PersonDetails
import com.codarchy.data.network.ChocolateFactoryApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(private val api: ChocolateFactoryApi) {

    var selectedEmployee: PersonDetails? = null

    suspend fun fetchEmployees(page: Int): ResultWrapper<EmployeeResults> = safeApiCall {
        api.getFactoryEmployees(page).toEmployeeResult()
    }

    suspend fun fetchEmployeeDetails(id: Int): ResultWrapper<PersonAdvancedDetails> = safeApiCall {
        api.getEmployeeDetails(id).toEmployeeDetail()
    }
}