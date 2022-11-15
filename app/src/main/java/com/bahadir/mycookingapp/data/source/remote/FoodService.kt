package com.bahadir.mycookingapp.data.source.remote

import com.bahadir.mycookingapp.common.Constants.INFORMATION
import com.bahadir.mycookingapp.common.Constants.RANDOM
import com.bahadir.mycookingapp.common.Constants.SEARCH
import com.bahadir.mycookingapp.common.Constants.SIMILAR
import com.bahadir.mycookingapp.data.model.remote.RandomFood
import com.bahadir.mycookingapp.data.model.remote.Recipe
import com.bahadir.mycookingapp.data.model.remote.SimilarItem
import com.bahadir.mycookingapp.data.model.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodService {
    @GET(RANDOM)
    suspend fun getRandomFood(
        @Query("number") count: Int
    ): RandomFood

    @GET(RANDOM)
    suspend fun getCategoryItem(
        @Query("number") count: Int,
        @Query("tags") category: String
    ): RandomFood

    @GET(INFORMATION)
    suspend fun getRecipe(
        @Path("id") id: Int,
    ): Recipe

    @GET(SIMILAR)
    suspend fun getSimilarRecipe(
        @Path("id") id: Int,
        @Query("number") size: Int
    ): List<SimilarItem>

    @GET(SEARCH)
    suspend fun searchRecipe(
        @Query("query") query: String,
        @Query("number") size: Int,
        @Query("diets") diets: String,
        @Query("cuisines") cuisine: String,
        @Query("intolerances") intolerance: String,
        @Query("type") type: String,
    ): SearchResponse
}
