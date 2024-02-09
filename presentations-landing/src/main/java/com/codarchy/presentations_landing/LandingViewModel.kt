package com.codarchy.presentations_landing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.PersonDetails
import com.codarchy.domain.list.HumanResourcesUseCase
import com.codarchy.domain.selection.SaveSelectedEmployeeUseCase
import com.codarchy.presentations_landing.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val humanResourcesUseCase: HumanResourcesUseCase,
    private val saveSelectedEmployeeUseCase: SaveSelectedEmployeeUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    val state = mutableStateOf<LandingScreenContent>(Loading)
    val data = mutableListOf<PersonDetails>()
    var currentPage = mutableStateOf(1)

    init {
        fetchEmployees()
    }

    fun fetchEmployees() {
        viewModelScope.launch {
            state.value = Loading
            withContext(ioDispatcher) {
                when(val employeeResponse = humanResourcesUseCase.invoke(1)) {
                    is ResultWrapper.NetworkError -> {
                        withContext(Dispatchers.Main) {
                            showNetworkError()
                        }

                    }
                    is ResultWrapper.GenericError -> {
                        withContext(Dispatchers.Main) {
                            showGenericError()
                        }
                    }
                    is ResultWrapper.Success -> {
                        if (employeeResponse.value.results.isNotEmpty()) {
                            state.value = PersonListReady(employeeResponse.value.results)
                            data.clear()
                            data.addAll(employeeResponse.value.results)
                        } else {
                            state.value = Empty
                        }
                    }
                }
            }
        }
    }

    private fun showGenericError() {
        state.value = GenericError
    }

    private fun showNetworkError() {
        state.value = NetworkError
    }

    fun onPersonClicked(person: PersonDetails) {
        saveSelectedEmployeeUseCase.invoke(person)
    }

    fun onSearch(query: String) {
        // todo implement this feature
    }
}


sealed class LandingScreenContent
data class PersonListReady(val shows: List<PersonDetails>) : LandingScreenContent()
object Loading : LandingScreenContent()
object Empty: LandingScreenContent()
object NetworkError: LandingScreenContent()
object GenericError: LandingScreenContent()