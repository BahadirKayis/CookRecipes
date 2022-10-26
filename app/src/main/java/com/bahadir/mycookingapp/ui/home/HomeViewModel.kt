package com.bahadir.mycookingapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.data.model.Menu
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.usecase.home.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getFood: HomeUseCase) : ViewModel() {

    private val _randomPopularity =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)
    val randomPopularity = _randomPopularity.asStateFlow()
    private val _menu =
        MutableStateFlow<Resource<List<Menu>>>(Resource.Loading)
    val menu = _menu.asStateFlow()


    init {
        getPopularity()
        getMenu()
    }

    private fun getPopularity() = viewModelScope.launch {
        getFood.getPopularity.invoke().collect {
            _randomPopularity.emit(it)
        }
    }

    private fun getMenu() = viewModelScope.launch {
        getFood.getMenu.invoke().collect {
            _menu.emit(it)

        }
    }


}