package com.bahadir.mycookingapp.data.model.remote.search


import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imageType")
    val imageType: String,
    @SerializedName("title")
    val title: String
)