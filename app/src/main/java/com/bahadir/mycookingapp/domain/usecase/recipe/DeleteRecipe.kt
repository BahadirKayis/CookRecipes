package com.bahadir.mycookingapp.domain.usecase.recipe

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class DeleteRecipe @Inject constructor(private val foodService: FoodRepository) {
    suspend operator fun invoke(recipeId: Int) = foodService.deleteRecipe(recipeId)
}
