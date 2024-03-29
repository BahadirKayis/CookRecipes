package com.bahadir.mycookingapp.domain.mapper

import com.bahadir.mycookingapp.common.extensions.emptyControl
import com.bahadir.mycookingapp.common.extensions.idToImageUrl
import com.bahadir.mycookingapp.data.model.remote.ExtendedIngredient
import com.bahadir.mycookingapp.data.model.remote.Recipe
import com.bahadir.mycookingapp.data.model.remote.SimilarItem
import com.bahadir.mycookingapp.data.model.remote.Step
import com.bahadir.mycookingapp.data.model.remote.search.SearchResult
import com.bahadir.mycookingapp.domain.model.*


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


fun List<ExtendedIngredient>.ingredientUI() = map {
    IngredientUI(
        id = it.id,
        name = it.original

    )
}


fun List<Step>.stepUI() = map {
    StepUI(
        step = it.step
    )
}

fun Recipe.recipeUI() = RecipeUI(
    id = id,
    title = title,
    dairyFree = dairyFree,
    glutenFree = glutenFree,
    aggregateLikes = aggregateLikes ?: 0,
    veryHealthy = veryHealthy,
    vegetarian = vegetarian,
    veryPopular = veryPopular,
    cheap = cheap,
    extendedIngredients = extendedIngredients.ingredientUI(),
    step = analyzedInstructions.emptyControl(),
    healthScore = healthScore,
    image = image,
    sourceUrl = sourceUrl,
    instructions = instructions,
    imageFilePath = ""
)

fun List<RandomFoodRecipeUI>.randomToSearchResultUI() = map {
    SearchResult(
        id = it.id,
        image = it.image,
        imageType = "jpg",
        title = it.title
    )
}






