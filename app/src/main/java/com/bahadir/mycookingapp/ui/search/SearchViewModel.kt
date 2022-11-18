package com.bahadir.mycookingapp.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.data.model.remote.search.SearchResult
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bahadir.mycookingapp.domain.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private var _getSearch = MutableStateFlow<Resource<List<SearchResult>>>(Resource.Loading)
    val getSearch = _getSearch.asStateFlow()


    private val _getRandom =
        MutableSharedFlow<Resource<List<RandomFoodRecipeUI>>>()
    val getRandom = _getRandom.asSharedFlow()


    init {
        getRandom()
    }

    fun getSearch(query: String, filter: Filter) = viewModelScope.launch {
        searchUseCase.getSearch.invoke(query, filter).onEach {
            _getSearch.emit(it)
            Log.e("TAG", "getSearch: $it")
        }.launchIn(viewModelScope)
    }

    private fun getRandom() = viewModelScope.launch {
        searchUseCase.getRandom.invoke().collect {
            _getRandom.emit(it)
        }
    }

}