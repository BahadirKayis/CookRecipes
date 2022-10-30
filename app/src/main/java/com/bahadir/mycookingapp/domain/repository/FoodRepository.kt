package com.bahadir.mycookingapp.domain.repository

import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.data.model.Menu
import com.bahadir.mycookingapp.data.model.recipe.Recipe

import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.model.SimilarRecipeUI
import kotlinx.coroutines.flow.Flow


interface FoodRepository {
    fun getPopularity(count: Int): Flow<Resource<List<RandomFoodRecipeUI>>>
    fun getMenu(): Flow<Resource<List<Menu>>>
    fun getMenuCategory(size:Int,category:String): Flow<Resource<List<RandomFoodRecipeUI>>>
    fun getRecipe(id:Int): Flow<Resource<Recipe>>
    fun getSimilar(id: Int,size:Int): Flow<Resource<List<SimilarRecipeUI>>>

}