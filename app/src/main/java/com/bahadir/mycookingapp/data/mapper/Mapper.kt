package com.bahadir.mycookingapp.data.mapper


import com.bahadir.mycookingapp.data.model.random.Recipe
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI

fun List<Recipe>.randomFoodToUI() = map {
    RandomFoodRecipeUI(
        id = it.id,
        image = it.image,
        title = it.title,
        readyInMinutes = it.readyInMinutes
    )

}





