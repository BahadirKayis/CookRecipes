package com.bahadir.mycookingapp.data.model.local

import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.data.model.Menu
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.data.model.remote.filter.FilterTypes

object CustomData {

    fun getFilterModel(mealType: Boolean = true): Filter {
        val diet = listOf(
            FilterTypes(0, "Gluten Free"),
            FilterTypes(1, "Ketogenic"),
            FilterTypes(2, "Vegetarian"),
            FilterTypes(3, "Vegan"),
            FilterTypes(4, "Pescetarian"),
            FilterTypes(5, "Whole30")
        )
        val country = listOf(
            FilterTypes(0, "African"),
            FilterTypes(1, "American"),
            FilterTypes(2, "British"),
            FilterTypes(3, "Chinese"),
            FilterTypes(4, "Mexican"),
            FilterTypes(5, "French"),
            FilterTypes(6, "German"),
            FilterTypes(7, "Korean"),
            FilterTypes(8, "Italian"),
        )
        val intolerances = listOf(
            FilterTypes(0, "Dairy"),
            FilterTypes(1, "Egg"),
            FilterTypes(2, "Gluten"),
            FilterTypes(3, "Grain"),
            FilterTypes(4, "Peanut"),
            FilterTypes(5, "Soy"),
            FilterTypes(6, "Tree Nut"),
        )
        val mealTypes = listOf(
            FilterTypes(0, "Breakfast"),
            FilterTypes(1, "Appetizer"),
            FilterTypes(2, "Soup"),
            FilterTypes(3, "Salad"),
            FilterTypes(4, "Bread"),
            FilterTypes(5, "Drink"),
            FilterTypes(6, "Sweets"),
            FilterTypes(7, "Main Course"),
        )
        return if (mealType) {
            Filter(diet, intolerances, country, mealTypes)
        } else {
            Filter(diet, intolerances, country, null)
        }


    }

    fun getMenu(): List<Menu> {
        return listOf(
            Menu(0, (R.drawable.sunnysideup), "Breakfast", R.color.breakfast),
            Menu(1, R.drawable.appetizer, "Appetizer", R.color.appetizer),
            Menu(2, R.drawable.soup, "Soup", R.color.soup),
            Menu(3, R.drawable.maincourse, "Main Course", R.color.main_course),
            Menu(4, R.drawable.salad, "Salad", R.color.salad),
            Menu(5, R.drawable.bakery, "Bread", R.color.bakery),
            Menu(6, R.drawable.juicebottle, "Drink", R.color.juice),
            Menu(7, R.drawable.sweets, "Sweets", R.color.sweets),
        )
    }
}
