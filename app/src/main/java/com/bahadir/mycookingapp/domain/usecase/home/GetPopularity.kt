package com.bahadir.mycookingapp.domain.usecase.home

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetPopularity @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke(count: Int) = foodRepository.getPopularity(count)

}