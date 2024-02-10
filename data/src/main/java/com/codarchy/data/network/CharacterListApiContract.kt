package com.codarchy.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("current") @Expose val current: Int,
    @SerializedName("total") @Expose val total: Int,
    @SerializedName("results") @Expose val results: List<CharacterDetails>
)

data class CharacterDetails(
    @SerializedName("first_name") @Expose val firstName: String,
    @SerializedName("last_name") @Expose val lastName: String,
    @SerializedName("favorite") @Expose val favorite: Favorite,
    @SerializedName("gender") @Expose val gender: String,
    @SerializedName("image") @Expose val image: String,
    @SerializedName("profession") @Expose val profession: String,
    @SerializedName("email") @Expose val email: String,
    @SerializedName("age") @Expose val age: Int,
    @SerializedName("country") @Expose val country: String,
    @SerializedName("height") @Expose val height: Int,
    @SerializedName("id") @Expose val id: Int
)

data class Favorite(
    @SerializedName("color") @Expose val color: String,
    @SerializedName("food") @Expose val food: String,
    @SerializedName("random_string") @Expose val randomString: String,
    @SerializedName("song") @Expose val song: String
)



