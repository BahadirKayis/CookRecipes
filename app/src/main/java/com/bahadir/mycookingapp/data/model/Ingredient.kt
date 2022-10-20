package com.bahadir.mycookingapp.data.model


import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("localizedName")
    val localizedName: String,
    @SerializedName("name")
    val name: String
)