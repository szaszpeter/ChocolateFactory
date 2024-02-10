package com.codarchy.data.model

data class CharacterAdvancedDetails(
    val lastName: String,
    val description: String,
    val image: String,
    val profession: String,
    val quota: String,
    val height: Int,
    val firstName: String,
    val country: String,
    val age: Int,
    val favorite: CharacterFavorite,
    val gender: String,
    val email: String
)

data class CharacterFavorite(
    val color: String,
    val food: String,
    val randomString: String,
    val song: String
)
