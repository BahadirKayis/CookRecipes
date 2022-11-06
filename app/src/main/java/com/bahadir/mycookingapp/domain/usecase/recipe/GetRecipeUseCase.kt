package com.bahadir.mycookingapp.domain.usecase.recipe

import javax.inject.Inject

data class GetRecipeUseCase @Inject constructor(
    val getRecipe: GetRecipe,
    val getSimilarRecipe: GetSimilarRecipe,
    val addRecipe: AddRecipe,
    val deleteRecipe: DeleteRecipe,
    val isRecipeSaved: IsRecipeSaved
)
