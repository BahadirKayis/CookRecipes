package com.bahadir.mycookingapp.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.data.model.recipe.Recipe
import com.bahadir.mycookingapp.data.model.similar.SimilarItem
import com.bahadir.mycookingapp.domain.usecase.recipe.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(private val recipeUseCase: GetRecipeUseCase) :
    ViewModel() {

    private val _recipe = MutableStateFlow<Resource<Recipe>>(Resource.Loading)
    val recipe = _recipe.asStateFlow()

    private val _similarRecipe = MutableStateFlow<Resource<List<SimilarItem>>>(Resource.Loading)
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