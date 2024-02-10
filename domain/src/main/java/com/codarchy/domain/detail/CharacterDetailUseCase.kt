package com.codarchy.domain.detail

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.CharacterAdvancedDetails
import com.codarchy.data.repository.CharacterRepository
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend operator fun invoke(id: Int): ResultWrapper<CharacterAdvancedDetails> {
        return repository.fetchCharacterDetails(id)
    }
}