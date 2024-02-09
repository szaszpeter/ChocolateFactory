package com.codarchy.presentation_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codarchy.data.model.PersonDetails
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
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    val state = mutableStateOf<ShowDetailUIState>(Empty)

    init {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                when (val selectedEmployee = retrieveSelectedEmployeeUseCase()) {
                    is PersonDetails -> {
                        withContext(mainDispatcher) {
                            state.value = ShowDetailsReady(selectedEmployee)
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


sealed class ShowDetailUIState
data class ShowDetailsReady(val person: PersonDetails) : ShowDetailUIState()
object Empty : ShowDetailUIState()