package com.bahadir.mycookingapp.ui.randomfood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.usecase.randomfood.FoodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(private val getFood: FoodUseCases) : ViewModel() {

    private val _randomBreakfast =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)
    val randomBreakfast = _randomBreakfast.asStateFlow()

    private val _randomSoup =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)
    val randomSoup = _randomSoup.asStateFlow()

    private val _randomMainCourse =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)
    val randomMainCourse = _randomMainCourse.asStateFlow()
    private val _randomAppetizer =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)
    val randomAppetizer = _randomAppetizer.asStateFlow()

    private val _randomSalad =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)
    val randomSalad = _randomSalad.asStateFlow()

    private val _randomDessert =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)
    val randomDessert = _randomDessert.asStateFlow()

    private val _randomDrink =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)
    val randomDrink = _randomDrink.asStateFlow()


    init {
        getBreakfast()
        getSoup()
        getMainCourse()
//        getAppetizer()
//        getSalad()
//        getDessert()
//        getDrink()
    }

    private fun getBreakfast() = viewModelScope.launch {
        getFood.getBreakfast.invoke().collect {
            _randomBreakfast.emit(it)
        }

    }

    private fun getSoup() = viewModelScope.launch {
        getFood.getSoup.invoke().collect {
            _randomSoup.emit(it)
        }
    }

    private fun getMainCourse() = viewModelScope.launch {
      getFood.getMainCourse.invoke().collect {
          _randomMainCourse.emit(it)
      }
    }


    private fun getAppetizer() = viewModelScope.launch {
        getFood.getAppetizer.invoke().collect {
            _randomAppetizer.emit(it)
        }

    }

    private fun getSalad() = viewModelScope.launch {
        getFood.getSalad.invoke().collect {
            _randomSalad.emit(it)
        }

    }

    private fun getDessert() = viewModelScope.launch {
    getFood.getDessert.invoke().collect {
        _randomDessert.emit(it)
    }
    }

    private fun getDrink() = viewModelScope.launch {
        getFood.getDrink.invoke().collect {
            _randomDrink.emit(it)
        }

    }
}