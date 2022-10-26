package com.bahadir.mycookingapp.domain.usecase.recipe

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetSimilarRecipe @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke(id:Int,size:Int) = foodRepository.getSimilar(id,size)
}