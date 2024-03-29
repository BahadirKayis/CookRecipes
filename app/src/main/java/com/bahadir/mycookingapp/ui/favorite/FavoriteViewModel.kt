package com.bahadir.mycookingapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.bahadir.mycookingapp.domain.usecase.favorite.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) :
    ViewModel() {

    private val tasksEventChannel = Channel<RecipeEvent>()
    val tasksEvent
        get() = tasksEventChannel.receiveAsFlow()

    private val _getAllRecipe = MutableStateFlow<Resource<List<RecipeUI>>>(Resource.Loading)
    val getAllRecipe
        get() = _getAllRecipe.asStateFlow()

    private val _deleteRecipe = MutableStateFlow<Resource<RecipeUI>>(Resource.Loading)
    val deleteRecipe
        get() = _deleteRecipe.asStateFlow()

    fun getFavoriteRecipes() = viewModelScope.launch {
        favoriteUseCase.getFavoriteRecipes.invoke().collect {
            _getAllRecipe.emit(it)
        }
    }

    fun onUndoRecipe(recipe: RecipeUI) = viewModelScope.launch {
        favoriteUseCase.addRecipe.invoke(recipe)
        getFavoriteRecipes()
    }

    fun deleteRecipe(recipe: RecipeUI) = viewModelScope.launch {
        favoriteUseCase.deleteRecipe.invoke(recipe)
        tasksEventChannel.send(RecipeEvent.ShowUndoDeleteRecipeMessage(recipe))
        getFavoriteRecipes()
    }

    sealed class RecipeEvent {
        data class ShowUndoDeleteRecipeMessage(val recipe: RecipeUI) : RecipeEvent()
    }

}