package com.bahadir.mycookingapp.domain.model


data class RandomFoodRecipeUI(
    val id: Int,
    val image: String?,
    val title: String,
    val healthScore: Int,
)