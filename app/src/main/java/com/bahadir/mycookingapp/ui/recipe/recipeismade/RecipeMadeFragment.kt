package com.bahadir.mycookingapp.ui.recipe.recipeismade


import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.gone
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.common.visible
import com.bahadir.mycookingapp.databinding.FragmentRecipeMadeBinding
import com.bahadir.mycookingapp.domain.model.RecipeUI


class RecipeMadeFragment : Fragment(R.layout.fragment_recipe_made) {
    private val binding by viewBinding(FragmentRecipeMadeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val step = if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkNotNull(
                arguments?.getParcelable(
                    "recipe",
                    RecipeUI::class.java
                )
            )
        } else {
            @Suppress("DEPRECATION")
            checkNotNull(arguments?.getParcelable("recipe"))
        }

        if (step.step != null) {

            val adapter = StepAdapter(step.step)
            binding.stepRecycler.adapter = adapter
        } else {
            binding.stepRecycler.gone()
            binding.image.visible()
            binding.instructions.visible()
            binding.instructions.text = step.instructions

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