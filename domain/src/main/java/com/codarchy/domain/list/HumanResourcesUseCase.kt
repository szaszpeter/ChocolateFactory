package com.codarchy.domain.list

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.EmployeeResults
import com.codarchy.data.repository.EmployeeRepository
import javax.inject.Inject

class HumanResourcesUseCase @Inject constructor(private val repository: EmployeeRepository) {

    suspend operator fun invoke(page: Int): ResultWrapper<EmployeeResults> {
        return repository.fetchEmployees(page)
    }
}