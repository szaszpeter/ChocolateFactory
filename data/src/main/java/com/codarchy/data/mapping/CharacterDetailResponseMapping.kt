package com.codarchy.data.mapping

import com.codarchy.data.model.CharacterAdvancedDetails
import com.codarchy.data.network.CharacterDetailResponse
import com.codarchy.data.network.CharacterFavorite

fun CharacterDetailResponse.toCharacterDetail(): CharacterAdvancedDetails = CharacterAdvancedDetails(
    lastName = lastName,
    description = description,
    image = image,
    profession = profession,
    quota = quota,
    height = height,
    firstName = firstName,
    country = country,
    age = age,
    favorite = personFavorite.toCharacterFavorite(),
    gender = gender,
    email = email,
)

fun CharacterFavorite.toCharacterFavorite(): com.codarchy.data.model.CharacterFavorite = com.codarchy.data.model.CharacterFavorite(
    color = color,
    food = food,
    randomString = randomString,
    song = song,
)