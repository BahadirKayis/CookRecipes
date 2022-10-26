package com.bahadir.mycookingapp.data.model

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val  image: Int,
    @SerializedName("title")
    val title: String,
    val backgroundColor: Int ,
)
