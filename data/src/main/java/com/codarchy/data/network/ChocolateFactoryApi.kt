package com.codarchy.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChocolateFactoryApi {

    @GET("napptilus/oompa-loompas")
    suspend fun getFactoryCharacters(@Query("page") page: Int): CharacterResponse

    @GET("napptilus/oompa-loompas/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): CharacterDetailResponse

}
