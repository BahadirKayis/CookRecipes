package com.bahadir.mycookingapp.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.command.ViewPagerAdapter
import com.bahadir.mycookingapp.databinding.FragmentRecipeBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint

//TODO args
@AndroidEntryPoint
class RecipeFragment : Fragment(R.layout.fragment_recipe) {
    private val binding by viewBinding(FragmentRecipeBinding::bind)
    private val viewModel: RecipeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //   cloudRequest()
        collectData()
        initializeView()
    }

    private fun cloudRequest() {
        with(viewModel) {
            getSimilarRecipe(716429, 10)
            getRecipe(716429)
        }
    }

    private fun collectData() {
        with(viewModel)
        {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                recipe.collect { response ->
                    when (response) {
                        is Resource.Loading -> Log.e("loading", "loading")
                        is Resource.Success -> {
//                            adapter.submitList(response.data)
                        }
                        is Resource.Error -> Log.e("throwable", response.throwable.toString())
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                similarRecipe.collect { response ->
                    when (response) {
                        is Resource.Loading -> Log.e("loading", "loading")
                        is Resource.Success -> {
                            //adapter.submitList(response.data)
                        }
                        is Resource.Error -> Log.e("throwable", response.throwable.toString())
                    }
                }
            }
        }
    }

    private fun initializeView() {
   val tabLayoutName = listOf("Recipe","Ingredient")
        with(binding) {
            viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
            TabLayoutMediator(tabLayout,viewPager) { tab, position ->
                tab.text = tabLayoutName[position]
            }.attach()

        }
    }
}