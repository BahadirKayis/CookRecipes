package com.bahadir.mycookingapp.data.model.local

import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.data.model.Menu
import com.bahadir.mycookingapp.data.model.remote.filter.*

object CustomData {

    fun getFilterModel(mealType: Boolean = true): Filter {
        val diet = listOf(
            Diets(0, "Gluten Free"),
            Diets(1, "Ketogenic"),
            Diets(2, "Vegetarian"),
            Diets(3, "Vegan"),
            Diets(4, "Pescetarian"),
            Diets(5, "Whole30")
        )


        val country = listOf(
            Country(0, "African"),
            Country(1, "American"),
            Country(2, "British"),
            Country(3, "Chinese"),
            Country(4, "Mexican"),
            Country(5, "French"),
            Country(6, "German"),
            Country(7, "Korean"),
            Country(8, "Italian"),

            )
        val intolerances = listOf(
            Intolerances(0, "Dairy"),
            Intolerances(1, "Egg"),
            Intolerances(2, "Gluten"),
            Intolerances(3, "Grain"),
            Intolerances(4, "Peanut"),
            Intolerances(5, "Soy"),
            Intolerances(6, "Tree Nut"),
        )
        val mealTypes = listOf(
            MealTypes(0, "Breakfast"),
            MealTypes(1, "Appetizer"),
            MealTypes(2, "Soup"),
            MealTypes(3, "Salad"),
            MealTypes(4, "Bread"),
            MealTypes(5, "Drink"),
            MealTypes(6, "Sweets"),
            MealTypes(7, "Main Course"),

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
