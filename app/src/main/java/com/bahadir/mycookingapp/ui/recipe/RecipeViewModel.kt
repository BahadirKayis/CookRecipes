package com.bahadir.mycookingapp.ui.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.common.Constants.STATE_KEY_RECIPE_ID
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
class RecipeViewModel @Inject constructor(
    private val recipeUseCase: GetRecipeUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recipe = MutableStateFlow<Resource<RecipeUI>>(Resource.Loading)
    val recipe = _recipe.asStateFlow()

    private val _similarRecipe = MutableStateFlow<Resource<List<SimilarRecipeUI>>>(Resource.Loading)
    val similarRecipe = _similarRecipe.asStateFlow()

    private val _isSavedRecipe = MutableStateFlow<Resource<Boolean>>(Resource.Loading)
    val isSavedRecipe = _isSavedRecipe.asStateFlow()

    init {
        savedStateHandle.get<Int>(STATE_KEY_RECIPE_ID)?.let {
            getSimilarRecipe(it)
            getRecipe(it)
            isRecipeSaved(it)
        }

    }

    private fun getRecipe(id: Int) = viewModelScope.launch {
        recipeUseCase.getRecipe.invoke(id).collect {
            _recipe.emit(it)
        }
    }

    private fun getSimilarRecipe(id: Int) = viewModelScope.launch {
        recipeUseCase.getSimilarRecipe.invoke(id, 15).collect {
            _similarRecipe.emit(it)
        }

    }

    private fun isRecipeSaved(recipeId: Int) = viewModelScope.launch {
        recipeUseCase.isRecipeSaved.invoke(recipeId).collect {
            _isSavedRecipe.emit(it)
        }
    }

    fun addRecipe(recipe: RecipeUI) = viewModelScope.launch {
        recipeUseCase.addRecipe.invoke(recipe)

        isRecipeSaved(recipe.id)
    }


    fun deleteRecipe(recipeId: Int) = viewModelScope.launch {
        recipeUseCase.deleteRecipe.invoke(recipeId)
        isRecipeSaved(recipeId)
    }


}