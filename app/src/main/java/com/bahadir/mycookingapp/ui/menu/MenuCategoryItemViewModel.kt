package com.bahadir.mycookingapp.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.usecase.menu.GetMenuCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuCategoryItemViewModel @Inject constructor(private val getMenu: GetMenuCategory) :
    ViewModel() {
    private val _menuCategoryItem =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)

    val menuCategoryItem = _menuCategoryItem.asStateFlow()

    fun getMenuCategoryItem(size: Int, category: String) = viewModelScope.launch {
        getMenu.invoke(size, category).collect {
            _menuCategoryItem.emit(it)
        }

    }
}