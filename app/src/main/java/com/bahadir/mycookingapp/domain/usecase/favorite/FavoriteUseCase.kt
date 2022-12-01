package com.bahadir.mycookingapp.domain.usecase.favorite

import com.bahadir.mycookingapp.domain.usecase.recipe.AddRecipe
import javax.inject.Inject

data class FavoriteUseCase @Inject constructor(
    val addRecipe: AddRecipe,
    val deleteRecipe: DeleteRecipe,
    val getFavoriteRecipes: GetFavoriteRecipes
)
