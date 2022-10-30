package com.bahadir.mycookingapp.ui.recipe.ingredient

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.data.model.random.ExtendedIngredient


class IngredientFragment : Fragment(R.layout.fragment_ingredient) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = checkNotNull(arguments?.getParcelableArrayList<ExtendedIngredient>("CONS"))
        Log.e("list", list.toString())

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<ExtendedIngredient>?) =
            IngredientFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("CONS", param1)

                }
            }
    }


}