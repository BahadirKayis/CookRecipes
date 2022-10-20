package com.bahadir.mycookingapp.data.source.remote

import com.bahadir.mycookingapp.command.Constants.RANDOM
import com.bahadir.mycookingapp.data.model.RandomFood
import com.bahadir.mycookingapp.data.model.Recipe
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET(RANDOM)
    suspend fun getRandomFood(
        @Query("apiKey") api:String,
        @Query("number") count: Int,
        @Query("tags") food: String
    ): RandomFood
}