package com.bahadir.mycookingapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class IngredientUI(
    val id: Int,
    val name: String
) : Parcelable