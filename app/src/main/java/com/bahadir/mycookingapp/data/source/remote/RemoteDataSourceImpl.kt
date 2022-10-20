package com.bahadir.mycookingapp.data.source.remote

import com.bahadir.mycookingapp.command.Constants.API_KEY
import com.bahadir.mycookingapp.data.model.RandomFood
import com.bahadir.mycookingapp.data.model.Recipe
import com.bahadir.mycookingapp.domain.source.remote.RemoteDataSource
import retrofit2.Response

import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val foodService: FoodService) :
    RemoteDataSource {
    override suspend fun getRandomFood(count: Int, food: String): RandomFood =
        foodService.getRandomFood(API_KEY,count, food)


}