package com.bahadir.mycookingapp.data.source.remote

import com.bahadir.mycookingapp.command.Constants.API_KEY
import com.bahadir.mycookingapp.data.model.random.RandomFood
import com.bahadir.mycookingapp.data.model.recipe.Recipe
import com.bahadir.mycookingapp.data.model.similar.SimilarItem
import com.bahadir.mycookingapp.domain.source.remote.RemoteDataSource
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val foodService: FoodService) :
    RemoteDataSource {
    override suspend fun getRandomFood(count: Int): RandomFood =
        foodService.getRandomFood(API_KEY, count)

    override suspend fun getCategoryItems(size: Int, category: String): RandomFood =
        foodService.getCategoryItem(API_KEY, size, category)

    override suspend fun getSimilarRecipe(id: Int, size: Int): List<SimilarItem> =
        foodService.getSimilarRecipe(API_KEY, id, size)

    override suspend fun getRecipe(id: Int, size: Int) : Recipe =foodService.getRecipe(API_KEY, id)


}