package com.bahadir.mycookingapp.ui.recipe.recipeismade


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.data.model.random.ExtendedIngredient
import com.bahadir.mycookingapp.databinding.FragmentRecipeMadeBinding


import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class RecipeMadeFragment : Fragment(R.layout.fragment_recipe_made) {
    private val binding by viewBinding(FragmentRecipeMadeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = checkNotNull(arguments?.getParcelableArrayList<ExtendedIngredient>("CONS"))
        Log.e("list", list.toString())

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<ExtendedIngredient>?) =
            RecipeMadeFragment().apply {
                arguments = Bundle().apply {
                    param1?.let { putParcelableArrayList("CONS", param1) }


                }
            }
    }

}