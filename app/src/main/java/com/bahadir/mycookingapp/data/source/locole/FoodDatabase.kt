package com.bahadir.mycookingapp.data.source.locole

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bahadir.mycookingapp.common.Converters
import com.bahadir.mycookingapp.domain.model.RecipeUI


@Database(
    entities = [RecipeUI::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}