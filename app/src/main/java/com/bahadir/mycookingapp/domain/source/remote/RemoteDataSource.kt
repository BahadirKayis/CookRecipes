package com.bahadir.mycookingapp.domain.source.remote


import com.bahadir.mycookingapp.data.model.remote.RandomFood
import com.bahadir.mycookingapp.data.model.remote.Recipe
import com.bahadir.mycookingapp.data.model.remote.SimilarItem
import com.bahadir.mycookingapp.data.model.remote.search.SearchResponse


interface RemoteDataSource {

    suspend fun getRandomFood(size: Int): RandomFood

    suspend fun getCategoryItems(size: Int, category: String): RandomFood

    suspend fun getSimilarRecipe(id: Int, size: Int): List<SimilarItem>

    suspend fun getRecipe(id: Int): Recipe

    suspend fun searchRecipe(
        query: String,
        diets: String,
        cuisine: String,
        intolerance: String,
        type: String
    ): SearchResponse
}