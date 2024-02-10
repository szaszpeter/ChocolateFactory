package com.codarchy.data.mapping

import com.codarchy.data.model.PersonAdvancedDetails
import com.codarchy.data.network.EmployeeDetailResponse
import com.codarchy.data.network.PersonFavorite

fun EmployeeDetailResponse.toEmployeeDetail(): PersonAdvancedDetails = PersonAdvancedDetails(
    lastName = lastName,
    description = description,
    image = image,
    profession = profession,
    quota = quota,
    height = height,
    firstName = firstName,
    country = country,
    age = age,
    favorite = personFavorite.toPersonFavorite(),
    gender = gender,
    email = email,
)

fun PersonFavorite.toPersonFavorite(): com.codarchy.data.model.PersonFavorite = com.codarchy.data.model.PersonFavorite(
    color = color,
    food = food,
    randomString = randomString,
    song = song,
)