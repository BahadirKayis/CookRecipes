package com.bahadir.mycookingapp.command

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bahadir.mycookingapp.ui.recipe.ingredient.IngredientFragment
import com.bahadir.mycookingapp.ui.recipe.recipeismade.RecipeMadeFragment


private const val NUM_TABS = 2

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return RecipeMadeFragment()

        }
        return IngredientFragment()
    }
}