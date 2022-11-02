package com.bahadir.mycookingapp.data.source.locole


import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.bahadir.mycookingapp.domain.source.locale.LocalDataSource

class LocalDataSourceImpl(private val foodDao: FoodDao) : LocalDataSource {
    override suspend fun addRecipe(recipe: RecipeUI) =foodDao.addRecipe(recipe)

    override suspend fun isSaveRecipe(recipeId: Int): RecipeUI? = foodDao.isTheSaveRecipe(recipeId)


}