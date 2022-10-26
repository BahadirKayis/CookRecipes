package com.bahadir.mycookingapp.data.source.remote

import com.bahadir.mycookingapp.command.Constants.INFORMATION
import com.bahadir.mycookingapp.command.Constants.RANDOM
import com.bahadir.mycookingapp.command.Constants.SIMILAR
import com.bahadir.mycookingapp.data.model.random.RandomFood
import com.bahadir.mycookingapp.data.model.recipe.Recipe
import com.bahadir.mycookingapp.data.model.similar.SimilarItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodService {
    @GET(RANDOM)
    suspend fun getRandomFood(
        @Query("apiKey") api: String,
        @Query("number") count: Int
    ): RandomFood

    @GET(RANDOM)
    suspend fun getCategoryItem(
        @Query("apiKey") api: String,
        @Query("number") count: Int,
        @Query("tags") category: String
    ): RandomFood

    @GET(INFORMATION)
    suspend fun getRecipe(
        @Query("apiKey") api: String,
        @Path("id") id: Int,
    ): Recipe

    @GET(SIMILAR)
    suspend fun getSimilarRecipe(
        @Query("apiKey") api: String,
        @Path("id") id: Int,
        @Query("number") size: Int
    ): List<SimilarItem>


}