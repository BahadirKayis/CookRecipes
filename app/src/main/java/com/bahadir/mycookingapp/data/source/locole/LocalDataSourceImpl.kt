package com.bahadir.mycookingapp.data.source.locole


import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.bahadir.mycookingapp.domain.source.locale.LocalDataSource

class LocalDataSourceImpl(private val foodDao: FoodDao) : LocalDataSource {
    override suspend fun addRecipe(recipe: RecipeUI) = foodDao.addRecipe(recipe)
    override suspend fun isSaveRecipe(recipeId: Int): RecipeUI = foodDao.isRecipeSaved(recipeId)
    override suspend fun deleteRecipe(recipeId: Int) = foodDao.deleteRecipe(recipeId)
    override suspend fun allRecipe(): List<RecipeUI> = foodDao.allRecipe()
    override suspend fun deleteRecipeFavorite(recipe: RecipeUI) =
        foodDao.deleteRecipeFavorite(recipe)

}