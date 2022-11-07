package com.bahadir.mycookingapp.domain.usecase.favorite

import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class DeleteRecipe @Inject constructor(private val foodService: FoodRepository) {
    suspend operator fun invoke(recipe: RecipeUI) = foodService.deleteFavoriteRecipe(recipe)
}
