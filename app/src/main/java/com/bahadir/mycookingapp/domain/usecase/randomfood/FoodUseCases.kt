package com.bahadir.mycookingapp.domain.usecase.randomfood

import javax.inject.Inject

data class FoodUseCases @Inject constructor (
    val getAppetizer: GetAppetizer,
    val getBreakfast: GetBreakfast,
    val getDessert: GetDessert,
    val getDrink: GetDrink,
    val getSalad: GetSalad,
    val getSoup: GetSoup,
    val getMainCourse: GetMainCourse,

)
