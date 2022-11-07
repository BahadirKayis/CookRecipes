package com.bahadir.mycookingapp.ui.menu

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bahadir.mycookingapp.data.mapper.randomFoodToUI
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.source.remote.RemoteDataSource

class Paging(
    private val remoteDataSource: RemoteDataSource,
    private val size: Int,
    private val categoryItem: String
) : PagingSource<Int, RandomFoodRecipeUI>() {
    override fun getRefreshKey(state: PagingState<Int, RandomFoodRecipeUI>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RandomFoodRecipeUI> {
        return try {
            val currentPage = params.key ?: 1
            val response = remoteDataSource.getCategoryItems(size, categoryItem)

            LoadResult.Page(
                data = response.recipes.randomFoodToUI(),
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}