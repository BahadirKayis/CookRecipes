package com.bahadir.mycookingapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StepUI(
    val step: String,
) : Parcelable