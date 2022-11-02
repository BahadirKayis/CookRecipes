package com.bahadir.mycookingapp.di

import android.content.Context
import androidx.room.Room
import com.bahadir.mycookingapp.data.source.locole.FoodDao
import com.bahadir.mycookingapp.data.source.locole.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RomDBModule {

    @Provides
    @Singleton
    fun provideFavoritesRoomDB(@ApplicationContext appContext: Context): FoodDatabase =
        Room.databaseBuilder(
            appContext,
            FoodDatabase::class.java,
            "recipeDatabase.db"
        ).build()

    @Provides
    @Singleton
    fun provideFoodDAO(foodDao: FoodDatabase): FoodDao =
        foodDao.foodDao()
}