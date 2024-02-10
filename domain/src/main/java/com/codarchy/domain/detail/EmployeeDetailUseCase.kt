package com.codarchy.domain.detail

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.PersonAdvancedDetails
import com.codarchy.data.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeDetailUseCase @Inject constructor(private val repository: EmployeeRepository) {

    suspend operator fun invoke(id: Int): ResultWrapper<PersonAdvancedDetails> {
        return repository.fetchEmployeeDetails(id)
    }
}