package com.bahadir.mycookingapp.domain.usecase.recipe

import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class AddRecipe @Inject constructor(private val foodService: FoodRepository) {
    suspend operator fun invoke(recipe: RecipeUI) = foodService.addRecipe(recipe)
}