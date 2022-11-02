package com.bahadir.mycookingapp.domain.usecase.home

import javax.inject.Inject

data class HomeUseCase @Inject constructor(
    val getMenu: GetMenu,
    val getPopularity: GetPopularity
)