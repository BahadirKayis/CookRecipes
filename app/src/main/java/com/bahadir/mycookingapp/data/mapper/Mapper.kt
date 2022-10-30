package com.bahadir.mycookingapp.data.mapper


import com.bahadir.mycookingapp.command.idToImageUrl
import com.bahadir.mycookingapp.data.model.random.Recipe
import com.bahadir.mycookingapp.data.model.similar.SimilarItem
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.model.SimilarRecipeUI

fun List<Recipe>.randomFoodToUI() = map {
    RandomFoodRecipeUI(
        id = it.id,
        image = it.image,
        title = it.title,
        healthScore = it.healthScore
    )
}
    fun List<SimilarItem>.similarUI() = map {
        SimilarRecipeUI(
            id = it.id,
            image = it.id.toString().idToImageUrl(it.imageType),
            title = it.title
        )
    }








