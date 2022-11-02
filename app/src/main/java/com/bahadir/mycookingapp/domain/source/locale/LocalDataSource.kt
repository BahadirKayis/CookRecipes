package com.bahadir.mycookingapp.domain.source.locale

import com.bahadir.mycookingapp.domain.model.RecipeUI


interface LocalDataSource {
    suspend fun addRecipe(recipe: RecipeUI)
    suspend fun isSaveRecipe(recipeId: Int): RecipeUI?
}