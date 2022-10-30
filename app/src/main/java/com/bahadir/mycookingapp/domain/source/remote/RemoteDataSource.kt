package com.bahadir.mycookingapp.domain.source.remote

import com.bahadir.mycookingapp.data.model.random.RandomFood
import com.bahadir.mycookingapp.data.model.recipe.Recipe
import com.bahadir.mycookingapp.data.model.similar.SimilarItem


interface RemoteDataSource {

    suspend fun getRandomFood(size: Int): RandomFood

    suspend fun getCategoryItems(size: Int, category: String): RandomFood

    suspend fun getSimilarRecipe(id: Int, size: Int): List<SimilarItem>

    suspend fun getRecipe(id: Int): Recipe
}