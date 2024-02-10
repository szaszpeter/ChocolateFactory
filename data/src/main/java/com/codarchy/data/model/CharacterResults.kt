package com.codarchy.data.model

data class CharacterResults(
    val current: Int,
    val total: Int,
    val results: List<CharacterDetails>
)

data class CharacterDetails(
    val firstName: String,
    val lastName: String,
    val favorite: Favorite,
    val gender: String,
    val image: String,
    val profession: String,
    val email: String,
    val age: Int,
    val country: String,
    val height: Int,
    val id: Int
)

data class Favorite(
    val color: String,
    val food: String,
    val randomString: String,
    val song: String
)

