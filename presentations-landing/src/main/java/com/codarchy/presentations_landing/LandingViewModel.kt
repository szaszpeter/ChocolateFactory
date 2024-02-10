package com.codarchy.presentations_landing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.CharacterDetails
import com.codarchy.domain.list.CharactersInventoryUseCase
import com.codarchy.domain.selection.SaveSelectedCharacterUseCase
import com.codarchy.presentations_landing.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val charactersInventoryUseCase: CharactersInventoryUseCase,
    private val saveSelectedCharacterUseCase: SaveSelectedCharacterUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    val state = mutableStateOf<LandingScreenContent>(Loading)
    val data = mutableListOf<CharacterDetails>()
    var currentPage = mutableStateOf(1)

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            state.value = Loading
            withContext(ioDispatcher) {
                when(val characterResponse = charactersInventoryUseCase.invoke(1)) {
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
                        if (characterResponse.value.results.isNotEmpty()) {
                            state.value = CharacterListReady(characterResponse.value.results)
                            data.clear()
                            data.addAll(characterResponse.value.results)
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

    fun onPersonClicked(person: CharacterDetails) {
        saveSelectedCharacterUseCase.invoke(person)
    }

    fun onSearch(query: String) {
        // todo implement this feature
    }
}


sealed class LandingScreenContent
data class CharacterListReady(val shows: List<CharacterDetails>) : LandingScreenContent()
object Loading : LandingScreenContent()
object Empty: LandingScreenContent()
object NetworkError: LandingScreenContent()
object GenericError: LandingScreenContent()