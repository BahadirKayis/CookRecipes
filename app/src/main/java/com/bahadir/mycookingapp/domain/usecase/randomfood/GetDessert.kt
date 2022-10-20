package com.bahadir.mycookingapp.domain.usecase.randomfood

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetDessert @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke() = foodRepository.getDessert(10, "dessert")

}