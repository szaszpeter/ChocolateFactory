package com.codarchy.data.mapping

import com.codarchy.data.model.CharacterResults
import com.codarchy.data.model.CharacterDetails
import com.codarchy.data.network.CharacterResponse
import com.codarchy.data.network.Favorite


fun CharacterResponse.toCharacterResult(): CharacterResults = CharacterResults(
    current = current,
    total = total,
    results = results.map { it.toCharacter() }
)

fun com.codarchy.data.network.CharacterDetails.toCharacter(): CharacterDetails = CharacterDetails(
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