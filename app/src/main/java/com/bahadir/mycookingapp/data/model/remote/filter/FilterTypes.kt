package com.bahadir.mycookingapp.data.model.remote.filter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FilterTypes(
    val id: Int,
    val name: String,
    var checked: Boolean = false,
) : Parcelable
