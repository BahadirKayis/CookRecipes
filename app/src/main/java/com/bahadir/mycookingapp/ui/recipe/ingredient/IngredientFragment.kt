package com.bahadir.mycookingapp.ui.recipe.ingredient

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.databinding.FragmentIngredientBinding
import com.bahadir.mycookingapp.domain.model.RecipeUI


class IngredientFragment : Fragment(R.layout.fragment_ingredient) {

    private val binding by viewBinding(FragmentIngredientBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ingredients = if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkNotNull(
                arguments?.getParcelable(
                    "ingredient",
                    RecipeUI::class.java
                )
            ).extendedIngredients
        } else {
            checkNotNull(arguments?.getParcelable<RecipeUI>("ingredient")).extendedIngredients
        }

        val adapter = IngredientAdapter(ingredients)
        binding.ingredientRecycler.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: RecipeUI) =
            IngredientFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("ingredient", param1)

                }
            }
    }


}