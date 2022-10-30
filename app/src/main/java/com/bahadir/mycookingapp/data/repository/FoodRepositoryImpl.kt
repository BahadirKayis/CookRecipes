package com.bahadir.mycookingapp.data.repository


import android.app.Application

import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.data.mapper.randomFoodToUI
import com.bahadir.mycookingapp.data.mapper.similarUI
import com.bahadir.mycookingapp.data.model.Menu
import com.bahadir.mycookingapp.data.model.recipe.Recipe
import com.bahadir.mycookingapp.data.source.remote.RemoteDataSourceImpl
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.model.SimilarRecipeUI
import com.bahadir.mycookingapp.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException


class FoodRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val app: Application
) :
    FoodRepository {
    override fun getPopularity(count: Int): Flow<Resource<List<RandomFoodRecipeUI>>> =
        flow {
            emit(Resource.Loading)
            val response = try {
                remoteDataSource.getRandomFood(count)


            } catch (e: IOException) {
                emit(Resource.Error(e))

                null
            }

            response?.let { data ->
                emit(Resource.Success(data.recipes.randomFoodToUI()))
            }
        }

    override fun getMenu(): Flow<Resource<List<Menu>>> =
        flow {
            val menuList: List<Menu> = listOf(
                Menu(0, (R.drawable.sunnysideup), "BreakFast", R.color.breakfast),
                Menu(1, R.drawable.appetizer, "Appetizer", R.color.appetizer),
                Menu(2, R.drawable.soup, "Soup", R.color.soup),
                Menu(3, R.drawable.maincourse, "Main Course", R.color.main_course),
                Menu(4, R.drawable.salad, "Salad", R.color.salad),
                Menu(5, R.drawable.bakery, "Bakery", R.color.bakery),
                Menu(6, R.drawable.juicebottle, "Juice", R.color.juice),
                Menu(7, R.drawable.sweets, "Sweets", R.color.sweets),


                )
            emit(Resource.Success(menuList))

        }

    override fun getMenuCategory(
        size: Int,
        category: String
    ): Flow<Resource<List<RandomFoodRecipeUI>>> =
        flow {
            emit(Resource.Loading)
            val response = try {
                remoteDataSource.getCategoryItems(size, category)


            } catch (e: IOException) {
                emit(Resource.Error(e))
                null
            }

            response?.let {
                emit(Resource.Success(it.recipes.randomFoodToUI()))
            }
        }

    override fun getRecipe(id: Int): Flow<Resource<Recipe>> =
        flow {
            val response = try {
                remoteDataSource.getRecipe(id)
            } catch (e: Throwable) {
                emit(Resource.Error(e))
                null

            }
            response?.let { emit(Resource.Success(it)) }


        }

    override fun getSimilar(id: Int, size: Int): Flow<Resource<List<SimilarRecipeUI>>> =
        flow {
            val response = try {
                remoteDataSource.getSimilarRecipe(id, size).similarUI()
            } catch (e: Throwable) {
                emit(Resource.Error(e))
                null
            }
            response?.let { emit(Resource.Success(it)) }
        }


}


