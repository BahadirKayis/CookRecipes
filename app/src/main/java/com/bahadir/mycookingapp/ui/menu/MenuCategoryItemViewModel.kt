package com.bahadir.mycookingapp.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.usecase.menu.GetMenuCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MenuCategoryItemViewModel @Inject constructor(
    private val getMenu: GetMenuCategory,
) :
    ViewModel() {


    fun getMenuCategoryItem(size: Int, category: String): Flow<PagingData<RandomFoodRecipeUI>> {
        return getMenu.invoke(size, category).cachedIn(viewModelScope)
    }


}