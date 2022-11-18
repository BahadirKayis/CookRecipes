package com.bahadir.mycookingapp.data.model.remote.filter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filter(
    var diet: List<Diets>,
    var intolerances: List<Intolerances>,
    var country: List<Country>,
    var mealTypes: List<MealTypes>?
) : Parcelable
