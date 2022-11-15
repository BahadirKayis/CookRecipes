package com.bahadir.mycookingapp.domain.usecase.search

import javax.inject.Inject

data class SearchUseCase @Inject constructor(val getSearch: GetSearch, val getRandom: GetRandom)