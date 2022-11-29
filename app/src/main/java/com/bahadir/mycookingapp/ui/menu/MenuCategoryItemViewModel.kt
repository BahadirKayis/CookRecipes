package com.bahadir.mycookingapp.ui.menu

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bahadir.mycookingapp.common.Constants.STATE_KEY_CATEGORY_NAME
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.usecase.menu.GetMenuCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuCategoryItemViewModel @Inject constructor(
    private val getMenuUseCase: GetMenuCategory, private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _getMenu = MutableStateFlow<PagingData<RandomFoodRecipeUI>>(PagingData.empty())
    val getMenu = _getMenu.asStateFlow()

    init {
        getMenuCategoryItem()
    }

    fun getMenuCategoryItem(
        filter: Filter? = null
    ) = viewModelScope.launch {
        savedStateHandle.get<String>(STATE_KEY_CATEGORY_NAME)?.let { key ->
            Log.e("TAG", "getMenuCategoryItem: " + key.lowercase())
            getMenuUseCase.invoke(20, key.lowercase(), filter).collect {
                _getMenu.emit(it)
            }
        }
    }
}



