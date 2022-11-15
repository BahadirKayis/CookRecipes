package com.bahadir.mycookingapp.domain.usecase.search

import com.bahadir.mycookingapp.data.model.filter.Filter
import com.bahadir.mycookingapp.domain.repository.FoodRepository
import javax.inject.Inject

class GetSearch @Inject constructor(private val foodRepository: FoodRepository) {
    suspend operator fun invoke(query: String, filter: Filter) =
        foodRepository.getSearch(query, filter)
}