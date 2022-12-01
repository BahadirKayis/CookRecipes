package com.bahadir.mycookingapp.domain.repository


import androidx.paging.PagingData
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.data.model.remote.search.SearchResult
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.bahadir.mycookingapp.domain.model.SimilarRecipeUI
import kotlinx.coroutines.flow.Flow


interface FoodRepository {
    fun getPopularity(count: Int): Flow<Resource<List<RandomFoodRecipeUI>>>
    fun getMenuCategory(
        size: Int,
        category: String,
        filterModel: Filter? = null
    ): Flow<PagingData<RandomFoodRecipeUI>>

    fun getRecipe(id: Int): Flow<Resource<RecipeUI>>
    fun getSimilar(id: Int, size: Int): Flow<Resource<List<SimilarRecipeUI>>>
    suspend fun addRecipe(recipe: RecipeUI)
    fun isRecipeSaved(recipeId: Int): Flow<Resource<Boolean>>
    suspend fun deleteRecipe(recipeId: Int)
    fun getFavoriteRecipes(): Flow<Resource<List<RecipeUI>>>
    suspend fun deleteFavoriteRecipe(recipeId: RecipeUI)
    fun getSearch(query: String, filterModel: Filter): Flow<Resource<List<SearchResult>>>
}