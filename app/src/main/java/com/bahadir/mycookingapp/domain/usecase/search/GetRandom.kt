package com.bahadir.mycookingapp.domain.usecase.search

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetRandom @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke() = foodRepository.getPopularity(50)
}