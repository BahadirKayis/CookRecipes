package com.bahadir.mycookingapp.domain.model

import com.google.gson.annotations.SerializedName

data class RandomFoodRecipeUI (
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,
        )