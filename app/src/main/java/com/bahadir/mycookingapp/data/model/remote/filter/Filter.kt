package com.bahadir.mycookingapp.data.model.remote.filter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filter(
    var diet: List<FilterTypes>,
    var intolerances: List<FilterTypes>,
    var country: List<FilterTypes>,
    var mealTypes: List<FilterTypes>?
) : Parcelable
