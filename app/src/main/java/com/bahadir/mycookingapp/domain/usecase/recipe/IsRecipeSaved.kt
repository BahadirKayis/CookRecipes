package com.bahadir.mycookingapp.domain.usecase.recipe

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class IsRecipeSaved @Inject constructor(private val foodService: FoodRepository) {
    operator fun invoke(recipeId: Int) = foodService.isRecipeSaved(recipeId)
}