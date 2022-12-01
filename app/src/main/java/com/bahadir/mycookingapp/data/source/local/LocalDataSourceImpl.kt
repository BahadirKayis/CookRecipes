package com.bahadir.mycookingapp.data.source.local


import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.bahadir.mycookingapp.domain.source.local.LocalDataSource

class LocalDataSourceImpl(private val foodDao: FoodDao) : LocalDataSource {
    override suspend fun addRecipe(recipe: RecipeUI) = foodDao.addRecipe(recipe)
    override suspend fun isSaveRecipe(recipeId: Int): RecipeUI = foodDao.isRecipeSaved(recipeId)
    override suspend fun deleteRecipe(recipeId: Int) = foodDao.deleteRecipe(recipeId)
    override suspend fun getFavoriteRecipes(): List<RecipeUI> = foodDao.getFavoriteRecipes()
    override suspend fun deleteRecipeFavorite(recipe: RecipeUI) =
        foodDao.deleteRecipeFavorite(recipe)

}