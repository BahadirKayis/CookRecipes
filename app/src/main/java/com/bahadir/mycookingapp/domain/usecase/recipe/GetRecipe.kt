package com.bahadir.mycookingapp.domain.usecase.recipe

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetRecipe @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke(id: Int) = foodRepository.getRecipe(id)
}