package com.bahadir.mycookingapp.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.command.ViewPagerAdapter
import com.bahadir.mycookingapp.data.model.random.ExtendedIngredient
import com.bahadir.mycookingapp.data.model.recipe.Recipe
import com.bahadir.mycookingapp.databinding.FragmentRecipeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeFragment : Fragment(R.layout.fragment_recipe) {
    private val binding by viewBinding(FragmentRecipeBinding::bind)
    private val viewModel: RecipeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cloudRequest()
        collectData()

    }

    private fun cloudRequest() {
        with(viewModel) {
            getSimilarRecipe(71642, 10)
            getRecipe(71642)
        }
    }

    private fun collectData() {
        with(viewModel)
        {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                recipe.collect { response ->
                    when (response) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            loadRecipe(response.data)
                        }
                        is Resource.Error -> Log.e(
                            "throwable-recipe",
                            response.throwable.toString()
                        )
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                similarRecipe.collect { response ->
                    when (response) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            val adapter = SimilarRecipeAdapter(response.data)
                            binding.similarRecipe.adapter = adapter
                            Log.e("response", response.data.toString())
                        }
                        is Resource.Error -> Log.e(
                            "throwable-similarRecipe",
                            response.throwable.toString()
                        )
                    }
                }
            }
        }
    }

    private fun loadRecipe(recipe: Recipe) {
        toolbar(recipe.title)
        foodImage(recipe.image)
        initializeView(recipe.extendedIngredients as ArrayList<ExtendedIngredient>)
    }

    private fun foodImage(url: String) {
        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(requireContext())
            .load(url)
            .override(500, 500) //1
            .diskCacheStrategy(DiskCacheStrategy.DATA) //6
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_baseline_no_photography_24)
            .into(binding.image)
    }

    private fun toolbar(foodName: String) {
        binding.toolbar.title = foodName
    }

    private fun initializeView(extendedIngredient: ArrayList<ExtendedIngredient>) {
        val tabLayoutName = listOf("Recipe", "Ingredient")
        with(binding) {
            viewPager.adapter =
                ViewPagerAdapter(childFragmentManager, lifecycle, extendedIngredient)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabLayoutName[position]
            }.attach()

        }
    }
}