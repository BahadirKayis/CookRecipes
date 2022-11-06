package com.bahadir.mycookingapp.di

import android.app.Application
import com.bahadir.mycookingapp.data.repository.FoodRepositoryImpl
import com.bahadir.mycookingapp.domain.repository.FoodRepository
import com.bahadir.mycookingapp.domain.source.locale.LocalDataSource
import com.bahadir.mycookingapp.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesFoodRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        application: Application
    ): FoodRepository = FoodRepositoryImpl(remoteDataSource, localDataSource, application)
}