package com.bahadir.mycookingapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "IngredientUI")
data class IngredientUI(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable