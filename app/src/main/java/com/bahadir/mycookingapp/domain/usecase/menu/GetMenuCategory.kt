package com.bahadir.mycookingapp.domain.usecase.menu

import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetMenuCategory @Inject constructor(private val foodRepository: FoodRepository) {
    operator fun invoke(size: Int, category: String, filter: Filter? = null) =
        foodRepository.getMenuCategory(size, category, filter)
}