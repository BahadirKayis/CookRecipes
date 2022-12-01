package com.bahadir.mycookingapp.data.source.local


import androidx.room.*
import com.bahadir.mycookingapp.domain.model.RecipeUI


@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: RecipeUI)

    @Query("SELECT * FROM recipe WHERE id=:recipeId")
    suspend fun isRecipeSaved(recipeId: Int): RecipeUI

    @Query("DELETE FROM recipe WHERE id=:recipeId")
    suspend fun deleteRecipe(recipeId: Int)

    @Query("SELECT * FROM recipe")
    suspend fun getFavoriteRecipes(): List<RecipeUI>

    @Delete
    suspend fun deleteRecipeFavorite(recipe: RecipeUI)

}