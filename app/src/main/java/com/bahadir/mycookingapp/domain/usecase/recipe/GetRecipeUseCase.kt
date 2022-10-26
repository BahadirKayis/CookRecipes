package com.bahadir.mycookingapp.domain.usecase.recipe

import javax.inject.Inject

data class GetRecipeUseCase @Inject constructor(
     val getRecipe: GetRecipe,
     val getSimilarRecipe: GetSimilarRecipe
)
