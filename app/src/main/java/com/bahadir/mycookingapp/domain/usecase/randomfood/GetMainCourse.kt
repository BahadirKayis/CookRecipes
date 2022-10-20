package com.bahadir.mycookingapp.domain.usecase.randomfood

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetMainCourse @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke() = foodRepository.getMainCourse(10, "main course")

}