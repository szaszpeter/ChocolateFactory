package com.codarchy.presentation_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.CharacterAdvancedDetails
import com.codarchy.domain.detail.CharacterDetailUseCase
import com.codarchy.domain.selection.RetrieveSelectedCharacterUseCase
import com.codarchy.presentation_detail.di.IoDispatcher
import com.codarchy.presentation_detail.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    private val retrieveSelectedCharacterUseCase: RetrieveSelectedCharacterUseCase,
    private val characterDetailUseCase: CharacterDetailUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    val state = mutableStateOf<CharacterDetailUIState>(Empty)

    init {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val selectedCharacter = retrieveSelectedCharacterUseCase()
                selectedCharacter?.let {
                    when (val characterAdvancedDetails =
                        characterDetailUseCase.invoke(selectedCharacter.id)) {
                        is ResultWrapper.Success -> {
                            withContext(mainDispatcher) {
                                state.value = CharacterDetailsReady(characterAdvancedDetails.value)
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


sealed class CharacterDetailUIState
data class CharacterDetailsReady(val person: CharacterAdvancedDetails) : CharacterDetailUIState()
object Empty : CharacterDetailUIState()