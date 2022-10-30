package com.bahadir.mycookingapp.command

fun String.titleCount(): String {
    return if (this.length > 20) {
        this.substring(0, 20) + "..."
    } else {
        this
    }
}

fun String.idToImageUrl(imgType: String): String {


    return "https://spoonacular.com/recipeImages/$this-556x370.$imgType"
}