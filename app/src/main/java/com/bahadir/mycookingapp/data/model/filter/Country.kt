package com.bahadir.mycookingapp.data.model.filter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Country(
    val id: Int,
    val name: String,
    var checked: Boolean = false,
) : Parcelable
