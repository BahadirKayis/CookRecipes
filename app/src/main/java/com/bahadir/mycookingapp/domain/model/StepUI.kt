package com.bahadir.mycookingapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "StepUI")
data class StepUI(
    @SerializedName("step")
    val step: String,
) : Parcelable