package com.bahadir.mycookingapp.domain.usecase.randomfood

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetAppetizer @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke() = foodRepository.getAppetizer(10, "appetizer")

}