package com.bahadir.mycookingapp.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.bahadir.mycookingapp.domain.model.SimilarRecipeUI
import com.bahadir.mycookingapp.domain.usecase.recipe.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(private val recipeUseCase: GetRecipeUseCase) :
    ViewModel() {

    private val _recipe = MutableStateFlow<Resource<RecipeUI>>(Resource.Loading)
    val recipe = _recipe.asStateFlow()

    private val _similarRecipe = MutableStateFlow<Resource<List<SimilarRecipeUI>>>(Resource.Loading)
    val similarRecipe = _similarRecipe.asStateFlow()


    fun getRecipe(id: Int) = viewModelScope.launch {
        recipeUseCase.getRecipe.invoke(id).collect {
            _recipe.emit(it)
        }
    }

    fun getSimilarRecipe(id: Int, size: Int) = viewModelScope.launch {
        recipeUseCase.getSimilarRecipe.invoke(id, size).collect {
            _similarRecipe.emit(it)
        }
    }
}