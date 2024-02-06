package com.codarchy.domain.selection

import com.codarchy.data.model.PersonDetails
import com.codarchy.data.repository.EmployeeRepository
import javax.inject.Inject

class RetrieveSelectedEmployeeUseCase @Inject constructor(private val repository: EmployeeRepository) {

    operator fun invoke(): PersonDetails? {
        return repository.selectedEmployee
    }
}