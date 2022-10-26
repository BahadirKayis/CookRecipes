package com.bahadir.mycookingapp.data.model.random


import com.google.gson.annotations.SerializedName

data class RandomFood(
    @SerializedName("recipes")
    val recipes: List<Recipe>
)