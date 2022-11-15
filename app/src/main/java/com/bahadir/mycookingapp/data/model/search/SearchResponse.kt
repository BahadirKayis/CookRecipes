package com.bahadir.mycookingapp.data.model.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("number")
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<SearchResult>,
    @SerializedName("totalResults")
    val totalResults: Int
)