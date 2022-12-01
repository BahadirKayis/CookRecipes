package com.bahadir.mycookingapp.domain.usecase.favorite

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetFavoriteRecipes @Inject constructor(private val foodService: FoodRepository) {
    operator fun invoke() = foodService.getFavoriteRecipes()
}