package com.codarchy.presentation_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.PersonAdvancedDetails
import com.codarchy.domain.detail.EmployeeDetailUseCase
import com.codarchy.domain.selection.RetrieveSelectedEmployeeUseCase
import com.codarchy.presentation_detail.di.IoDispatcher
import com.codarchy.presentation_detail.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    private val retrieveSelectedEmployeeUseCase: RetrieveSelectedEmployeeUseCase,
    private val employeeDetailUseCase: EmployeeDetailUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    val state = mutableStateOf<ShowDetailUIState>(Empty)

    init {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val selectedEmployee = retrieveSelectedEmployeeUseCase()
                selectedEmployee?.let {
                    when (val personAdvancedDetails =
                        employeeDetailUseCase.invoke(selectedEmployee.id)) {
                        is ResultWrapper.Success -> {
                            withContext(mainDispatcher) {
                                state.value = CharacterDetailsReady(personAdvancedDetails.value)
                            }
                        }

                        else -> {
                            withContext(mainDispatcher) {
                                state.value = Empty
                            }
                        }
                    }
                }


            }
        }
    }
}


sealed class ShowDetailUIState
data class CharacterDetailsReady(val person: PersonAdvancedDetails) : ShowDetailUIState()
object Empty : ShowDetailUIState()