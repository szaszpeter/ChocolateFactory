package com.codarchy.data.repository

import com.codarchy.data.errorhandling.ResultWrapper
import com.codarchy.data.errorhandling.safeApiCall
import com.codarchy.data.mapping.toCharacterDetail
import com.codarchy.data.mapping.toCharacterResult
import com.codarchy.data.model.CharacterResults
import com.codarchy.data.model.CharacterAdvancedDetails
import com.codarchy.data.model.CharacterDetails
import com.codarchy.data.network.ChocolateFactoryApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(private val api: ChocolateFactoryApi) {

    var selectedCharacter: CharacterDetails? = null

    suspend fun fetchCharacters(page: Int): ResultWrapper<CharacterResults> = safeApiCall {
        api.getFactoryCharacters(page).toCharacterResult()
    }

    suspend fun fetchCharacterDetails(id: Int): ResultWrapper<CharacterAdvancedDetails> = safeApiCall {
        api.getCharacterDetails(id).toCharacterDetail()
    }
}