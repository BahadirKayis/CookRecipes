package com.bahadir.mycookingapp.data.source.locole

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


import com.bahadir.mycookingapp.domain.model.RecipeUI


@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: RecipeUI)

    @Query("SELECT * FROM recipe WHERE id=:recipeId")
    suspend fun isTheSaveRecipe(recipeId: Int): RecipeUI?

}