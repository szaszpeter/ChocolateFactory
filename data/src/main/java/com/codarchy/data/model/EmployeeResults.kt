package com.codarchy.data.model

data class EmployeeResults(
    val current: Int,
    val total: Int,
    val results: List<PersonDetails>
)

data class PersonDetails(
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

