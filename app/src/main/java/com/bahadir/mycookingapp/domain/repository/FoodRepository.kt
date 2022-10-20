package com.bahadir.mycookingapp.domain.repository

import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import kotlinx.coroutines.flow.Flow


interface FoodRepository {
    fun getBreakfast(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>>
    fun getSoup(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>>
    fun getMainCourse(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>>
    fun getAppetizer(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>>
    fun getSalad(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>>
    fun getDessert(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>>
    fun getDrink(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>>
}