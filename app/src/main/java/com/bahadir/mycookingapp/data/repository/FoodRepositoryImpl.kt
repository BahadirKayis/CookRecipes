package com.bahadir.mycookingapp.data.repository


import android.util.Log
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.data.mapper.randomFoodToUI
import com.bahadir.mycookingapp.data.source.remote.RemoteDataSourceImpl
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException


class FoodRepositoryImpl constructor(private val remoteDataSource: RemoteDataSourceImpl) :
    FoodRepository {
    override fun getBreakfast(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>> =
        flow {
            emit(Resource.Loading)
            val response = try {
                remoteDataSource.getRandomFood(count, food)


            } catch (e: IOException) {
                emit(Resource.Error(e))
                Log.i("tag", e.toString())
                null
            }
            Log.i("tag", response.toString())
            response?.let { data ->
                emit(Resource.Success(data.recipes.randomFoodToUI()))
            }
        }

    override fun getSoup(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>> =
        flow {
            emit(Resource.Loading)
            val response = try {
                remoteDataSource.getRandomFood(count, food)


            } catch (e: IOException) {
                emit(Resource.Error(e))
                Log.i("tag", e.toString())
                null
            }
            Log.i("tag", response.toString())
            response?.let { data ->
                emit(Resource.Success(data.recipes.randomFoodToUI()))
            }
        }

    override fun getMainCourse(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>> =
        flow {
            emit(Resource.Loading)
            val response = try {
                remoteDataSource.getRandomFood(count, food)


            } catch (e: IOException) {
                emit(Resource.Error(e))
                Log.i("tag", e.toString())
                null
            }
            Log.i("tag", response.toString())
            response?.let { data ->
                emit(Resource.Success(data.recipes.randomFoodToUI()))
            }
        }

    override fun getAppetizer(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>> =
        flow {
            emit(Resource.Loading)
            val response = try {
                remoteDataSource.getRandomFood(count, food)


            } catch (e: IOException) {
                emit(Resource.Error(e))
                Log.i("tag", e.toString())
                null
            }
            Log.i("tag", response.toString())
            response?.let { data ->
                emit(Resource.Success(data.recipes.randomFoodToUI()))
            }
        }

    override fun getSalad(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>> =
        flow {
            emit(Resource.Loading)
            val response = try {
                remoteDataSource.getRandomFood(count, food)


            } catch (e: IOException) {
                emit(Resource.Error(e))
                Log.i("tag", e.toString())
                null
            }
            Log.i("tag", response.toString())
            response?.let { data ->
                emit(Resource.Success(data.recipes.randomFoodToUI()))
            }
        }

    override fun getDessert(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>> =
        flow {
            emit(Resource.Loading)
            val response = try {
                remoteDataSource.getRandomFood(count, food)


            } catch (e: IOException) {
                emit(Resource.Error(e))
                Log.i("tag", e.toString())
                null
            }
            Log.i("tag", response.toString())
            response?.let { data ->
                emit(Resource.Success(data.recipes.randomFoodToUI()))
            }
        }

    override fun getDrink(count: Int, food: String): Flow<Resource<List<RandomFoodRecipeUI>>> =
        flow {
            emit(Resource.Loading)
            val response = try {
                remoteDataSource.getRandomFood(count, food)


            } catch (e: IOException) {
                emit(Resource.Error(e))
                Log.i("tag", e.toString())
                null
            }
            Log.i("tag", response.toString())
            response?.let { data ->
                emit(Resource.Success(data.recipes.randomFoodToUI()))
            }
        }

}


