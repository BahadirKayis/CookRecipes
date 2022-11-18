package com.bahadir.mycookingapp.data.source.remote

import com.bahadir.mycookingapp.data.model.remote.RandomFood
import com.bahadir.mycookingapp.data.model.remote.Recipe
import com.bahadir.mycookingapp.data.model.remote.SimilarItem
import com.bahadir.mycookingapp.data.model.remote.search.SearchResponse
import com.bahadir.mycookingapp.domain.source.remote.RemoteDataSource


class RemoteDataSourceImpl(private val foodService: FoodService) :
    RemoteDataSource {
    override suspend fun getRandomFood(size: Int): RandomFood =
        foodService.getRandomFood(size)

    override suspend fun getCategoryItems(size: Int, category: String): RandomFood =
        foodService.getCategoryItem(size, category)

    override suspend fun getSimilarRecipe(id: Int, size: Int): List<SimilarItem> =
        foodService.getSimilarRecipe(id, size)

    override suspend fun getRecipe(id: Int): Recipe = foodService.getRecipe(id)
    override suspend fun searchRecipe(
        query: String,
        diets: String,
        cuisine: String,
        intolerance: String,
        type: String
    ): SearchResponse =
        foodService.searchRecipe(query, 50, diets, cuisine, intolerance, type)


}