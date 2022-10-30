package com.bahadir.mycookingapp.data.model.random


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Us(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("unitLong")
    val unitLong: String,
    @SerializedName("unitShort")
    val unitShort: String
): Parcelable