package com.bahadir.mycookingapp.data.repository


import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.common.imageDownloadSaveFile
import com.bahadir.mycookingapp.data.mapper.randomFoodToUI
import com.bahadir.mycookingapp.data.mapper.recipeUI
import com.bahadir.mycookingapp.data.mapper.similarUI
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.data.model.remote.search.SearchResult
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


    override fun getMenuCategory(
        size: Int, category: String, filterModel: Filter?
    ): Flow<PagingData<RandomFoodRecipeUI>> = flow {

        var categoryFilter = "$category,"
        filterModel?.let {
            filterModel.diet.forEach { if (it.checked) categoryFilter += it.name.lowercase() + "," }
            filterModel.country.forEach { if (it.checked) categoryFilter += it.name.lowercase() + "," }
            filterModel.intolerances.forEach { if (it.checked) categoryFilter += it.name.lowercase() + "," }
        }


        Pager(config = PagingConfig(size, maxSize = 100, enablePlaceholders = false),
            pagingSourceFactory = {

                Paging(
                    remoteDataSource = remoteDataSource, size, categoryFilter
                )
            }).flow.collect { emit(it) }


    }


    override fun getRecipe(id: Int): Flow<Resource<RecipeUI>> = flow {
        val response = try {
            remoteDataSource.getRecipe(id)
        } catch (e: Throwable) {
            emit(Resource.Error(e))
            null
        }
        response?.let { emit(Resource.Success(it.recipeUI())) }


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

    override fun allRecipe(): Flow<Resource<List<RecipeUI>>> = flow {
        emit(Resource.Loading)
        val response = try {
            localDataSource.allRecipe()
        } catch (e: Throwable) {
            emit(Resource.Error(e))
            null
        }
        response?.let { emit(Resource.Success(it)) }

    }

    override suspend fun deleteFavoriteRecipe(recipeId: RecipeUI) =
        localDataSource.deleteRecipeFavorite(recipeId)

    override suspend fun getSearch(
        query: String, filterModel: Filter
    ): Flow<Resource<List<SearchResult>>> = flow {

        emit(Resource.Loading)
        var diet = ""
        var country = ""
        var intolerances = ""
        var mealType = ""
        filterModel.let {
            filterModel.diet.forEach { if (it.checked) diet += it.name.lowercase() + "," }
            filterModel.country.forEach { if (it.checked) country += it.name.lowercase() + "," }
            filterModel.intolerances.forEach { if (it.checked) intolerances += it.name.lowercase() + "," }
            filterModel.mealTypes?.forEach { if (it.checked) mealType += it.name.lowercase() + "," }
        }


        val response = try {
            remoteDataSource.searchRecipe(
                query, diet, country, intolerances, mealType
            )
        } catch (e: Throwable) {
            emit(Resource.Error(e))
            null
        }

        response?.let { emit(Resource.Success(it.results)) }
    }


}


