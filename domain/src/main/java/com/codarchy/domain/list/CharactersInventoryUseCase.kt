package com.codarchy.domain.list

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.model.CharacterResults
import com.codarchy.data.repository.CharacterRepository
import javax.inject.Inject

class CharactersInventoryUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend operator fun invoke(page: Int): ResultWrapper<CharacterResults> {
        return repository.fetchCharacters(page)
    }
}