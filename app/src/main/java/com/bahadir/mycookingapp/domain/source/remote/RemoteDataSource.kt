package com.bahadir.mycookingapp.domain.source.remote

import com.bahadir.mycookingapp.data.model.RandomFood


interface RemoteDataSource {

    suspend fun getRandomFood(count: Int, food: String): RandomFood
}