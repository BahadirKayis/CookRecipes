package com.bahadir.mycookingapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.usecase.home.GetPopularity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPopularity: GetPopularity) : ViewModel() {

    private val _randomPopularity =
        MutableStateFlow<Resource<List<RandomFoodRecipeUI>>>(Resource.Loading)
    val randomPopularity = _randomPopularity.asStateFlow()


    init {
        getPopularity()
    }

    private fun getPopularity() = viewModelScope.launch {
        getPopularity.invoke().collect {
            _randomPopularity.emit(it)
        }
    }


}