package com.bahadir.mycookingapp.data.repository


import android.app.Application
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.common.imageDownloadSaveFile
import com.bahadir.mycookingapp.common.pars
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.data.model.remote.search.SearchResult
import com.bahadir.mycookingapp.domain.mapper.randomFoodToUI
import com.bahadir.mycookingapp.domain.mapper.recipeUI
import com.bahadir.mycookingapp.domain.mapper.similarUI
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.bahadir.mycookingapp.domain.model.SimilarRecipeUI
import com.bahadir.mycookingapp.domain.repository.FoodRepository
import com.bahadir.mycookingapp.domain.source.local.LocalDataSource
import com.bahadir.mycookingapp.domain.source.remote.RemoteDataSource
import com.bahadir.mycookingapp.ui.menu.Paging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException


class FoodRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val app: Application
) : FoodRepository {

    override fun getPopularity(count: Int): Flow<Resource<List<RandomFoodRecipeUI>>> = flow {
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

    override fun getRecipe(id: Int): Flow<Resource<RecipeUI>> = flow {
        val response = try {
            remoteDataSource.getRecipe(id)
        } catch (e: Throwable) {
            emit(Resource.Error(e))
            null
        }
        response?.let {
            Log.e("getRecipe", it.analyzedInstructions.toString())

            emit(Resource.Success(it.recipeUI()))


        }


    }

    override fun getSimilar(id: Int, size: Int): Flow<Resource<List<SimilarRecipeUI>>> = flow {
        val response = try {
            remoteDataSource.getSimilarRecipe(id, size).similarUI()
        } catch (e: Throwable) {
            emit(Resource.Error(e))
            null
        }

        response?.let { emit(Resource.Success(it)) }
    }

    override suspend fun addRecipe(recipe: RecipeUI) {
        val imagePath = recipe.id.toString() + recipe.title + ".png"

        recipe.imageFilePath = app.imageDownloadSaveFile(imagePath, recipe.image)
        localDataSource.addRecipe(recipe)

    }

    override fun isRecipeSaved(recipeId: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)

        val response = try {

            localDataSource.isSaveRecipe(recipeId)
        } catch (e: Throwable) {
            emit(Resource.Error(e))
            null
        }

        response?.let {
            emit(Resource.Success(true))
        } ?: run { emit(Resource.Success(false)) }

    }

    override suspend fun deleteRecipe(recipeId: Int) {

        localDataSource.deleteRecipe(recipeId)
    }

    override fun getFavoriteRecipes(): Flow<Resource<List<RecipeUI>>> = flow {
        emit(Resource.Loading)
        val response = try {
            localDataSource.getFavoriteRecipes()
        } catch (e: Throwable) {
            emit(Resource.Error(e))
            null
        }
        response?.let { emit(Resource.Success(it)) }

    }

    override suspend fun deleteFavoriteRecipe(recipeId: RecipeUI) =
        localDataSource.deleteRecipeFavorite(recipeId)

    override fun getSearch(
        query: String, filterModel: Filter
    ): Flow<Resource<List<SearchResult>>> = flow {

        emit(Resource.Loading)
        with(filterModel) {
            val diet = diet.pars()
            val country = country.pars()
            val intolerances = intolerances.pars()
            val mealType = mealTypes?.pars()


            val response = try {
                remoteDataSource.searchRecipe(
                    query, diet, country, intolerances, mealType ?: ""
                )
            } catch (e: Throwable) {
                emit(Resource.Error(e))
                null
            }

            response?.let { emit(Resource.Success(it.results)) }
        }
    }

    override fun getMenuCategory(
        size: Int, category: String, filterModel: Filter?
    ): Flow<PagingData<RandomFoodRecipeUI>> = flow {

        val filterType =
            filterModel?.let { it.diet.pars() + it.country.pars() + it.intolerances.pars() }
                ?: run { "" }
        val categoryFilter = "$category,${filterType}"

        Log.e("TAG", "getMenuCategory: $categoryFilter")

        Pager(config = PagingConfig(size, maxSize = 100, enablePlaceholders = false),
            pagingSourceFactory = {

                Paging(
                    remoteDataSource = remoteDataSource, size, category
                )
            }).flow.collect { emit(it) }


    }
}


