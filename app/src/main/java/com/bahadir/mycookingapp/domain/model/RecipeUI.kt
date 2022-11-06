package com.bahadir.mycookingapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "recipe")
data class RecipeUI(
    @PrimaryKey(autoGenerate = false)

    val id: Int,

    val title: String,

    val dairyFree: Boolean,

    val glutenFree: Boolean,

    val aggregateLikes: Int,

    val veryHealthy: Boolean,

    val vegetarian: Boolean,

    val veryPopular: Boolean,

    val cheap: Boolean,

    val extendedIngredients: List<IngredientUI>,

    val step: List<StepUI>?,

    val healthScore: Int,

    val image: String,

    val sourceUrl: String,

    val instructions: String,

    var imageFilePath: String?,
) : Parcelable
