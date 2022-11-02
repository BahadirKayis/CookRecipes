package com.bahadir.mycookingapp.ui.recipe.recipeismade


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.databinding.FragmentRecipeMadeBinding
import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class RecipeMadeFragment : Fragment(R.layout.fragment_recipe_made) {
    private val binding by viewBinding(FragmentRecipeMadeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = checkNotNull(arguments?.getParcelable<RecipeUI>("recipe"))
        Log.e("list", (list.step.toString()))
        if (list.step.isNotEmpty()) {
            val adapter = StepAdapter(list.step)
            binding.stepRecycler.adapter = adapter
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: RecipeUI) =
            RecipeMadeFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("recipe", param1)

                }
            }
    }

}