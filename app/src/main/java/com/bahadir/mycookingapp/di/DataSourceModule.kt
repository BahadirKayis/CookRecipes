package com.bahadir.mycookingapp.di

import com.bahadir.mycookingapp.data.source.local.FoodDao
import com.bahadir.mycookingapp.data.source.local.LocalDataSourceImpl
import com.bahadir.mycookingapp.data.source.remote.FoodService
import com.bahadir.mycookingapp.data.source.remote.RemoteDataSourceImpl
import com.bahadir.mycookingapp.domain.source.local.LocalDataSource
import com.bahadir.mycookingapp.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(foodService: FoodService): RemoteDataSource =
        RemoteDataSourceImpl(foodService)

    @Provides
    @Singleton
    fun provideLocalDataSource(localService: FoodDao): LocalDataSource =
        LocalDataSourceImpl(localService)
}