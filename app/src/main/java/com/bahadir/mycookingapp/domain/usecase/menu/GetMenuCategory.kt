package com.bahadir.mycookingapp.domain.usecase.menu

import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetMenuCategory @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke(size: Int, category: String) =
        foodRepository.getMenuCategory(size, category)
}