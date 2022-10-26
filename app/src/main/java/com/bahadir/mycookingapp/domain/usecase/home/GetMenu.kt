package com.bahadir.mycookingapp.domain.usecase.home

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetMenu @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke() = foodRepository.getMenu()

}