package com.codarchy.data.mapping

import com.codarchy.data.model.EmployeeResults
import com.codarchy.data.model.PersonDetails
import com.codarchy.data.network.EmployeeResponse
import com.codarchy.data.network.Favorite


fun EmployeeResponse.toEmployeeResult(): EmployeeResults = EmployeeResults(
    current = current,
    total = total,
    results = results.map { it.toPersonDetails() }
)

fun com.codarchy.data.network.PersonDetails.toPersonDetails(): PersonDetails = PersonDetails(
    firstName = firstName,
    lastName = lastName,
    favorite = favorite.toFavorite(),
    gender = gender,
    image = image,
    profession = profession,
    email = email,
    age = age,
    country = country,
    height = height,
    id = id,
)

fun Favorite.toFavorite(): com.codarchy.data.model.Favorite = com.codarchy.data.model.Favorite(
    color = color,
    food = food,
    randomString = randomString,
    song = song,
)