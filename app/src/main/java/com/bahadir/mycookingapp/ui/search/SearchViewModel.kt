package com.bahadir.mycookingapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.data.model.remote.search.SearchResult
import com.bahadir.mycookingapp.domain.mapper.randomToSearchResultUI
import com.bahadir.mycookingapp.domain.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private var _getSearch = MutableStateFlow<Resource<List<SearchResult>>>(Resource.Loading)
    val getSearch
        get() = _getSearch.asStateFlow()

    init {
        getRandom()
    }

    fun getSearch(query: String, filter: Filter) = viewModelScope.launch {
        searchUseCase.getSearch.invoke(query, filter).collect {
            _getSearch.emit(it)
        }
    }

    private fun getRandom() = viewModelScope.launch {
        searchUseCase.getRandom.invoke().collect {
            when (it) {
                is Resource.Success -> {
                    val data = it.data.randomToSearchResultUI()
                    _getSearch.emit(Resource.Success(data))
                }
                is Resource.Error -> {
                    _getSearch.emit(it)
                }
                is Resource.Loading -> {
                    _getSearch.emit(it)
                }
            }
        }
    }

}