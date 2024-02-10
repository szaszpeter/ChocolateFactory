package com.codarchy.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterDetailResponse(
    @SerializedName("last_name") @Expose val lastName: String,
    @SerializedName("description") @Expose val description: String,
    @SerializedName("image") @Expose val image: String,
    @SerializedName("profession") @Expose val profession: String,
    @SerializedName("quota") @Expose val quota: String,
    @SerializedName("height") @Expose val height: Int,
    @SerializedName("first_name") @Expose val firstName: String,
    @SerializedName("country") @Expose val country: String,
    @SerializedName("age") @Expose val age: Int,
    @SerializedName("favorite") @Expose val personFavorite: CharacterFavorite,
    @SerializedName("gender") @Expose val gender: String,
    @SerializedName("email") @Expose val email: String
)

data class CharacterFavorite(
    @SerializedName("color") @Expose val color: String,
    @SerializedName("food") @Expose val food: String,
    @SerializedName("random_string") @Expose val randomString: String,
    @SerializedName("song") @Expose val song: String
)