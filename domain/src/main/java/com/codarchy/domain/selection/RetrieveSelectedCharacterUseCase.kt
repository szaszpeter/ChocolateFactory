package com.codarchy.domain.selection

import com.codarchy.data.model.CharacterDetails
import com.codarchy.data.repository.CharacterRepository
import javax.inject.Inject

class RetrieveSelectedCharacterUseCase @Inject constructor(private val repository: CharacterRepository) {

    operator fun invoke(): CharacterDetails? {
        return repository.selectedCharacter
    }
}