package com.bahadir.mycookingapp.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.*

import com.bahadir.mycookingapp.databinding.FragmentRecipeBinding
import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint


//TODO is liked the recipe save to room
//Todo not working
@AndroidEntryPoint
class RecipeFragment : Fragment(R.layout.fragment_recipe) {
    private val binding by viewBinding(FragmentRecipeBinding::bind)
    private val viewModel: RecipeViewModel by viewModels()
    private val args: RecipeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cloudRequest()
        collectData()

        isSave()
    }


    private fun isSave() {
        with(binding) {

        }
    }

    private fun cloudRequest() {
        with(viewModel) {
            getSimilarRecipe(args.recipeId, 10)
            getRecipe(args.recipeId)
        }
    }

    private fun collectData() {
        with(viewModel)
        {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                recipe.collect { response ->
                    when (response) {
                        is Resource.Loading -> {
                            binding.animLoading.visible()
                        }
                        is Resource.Success -> {
                            binding.animLoading.gone()
                            loadRecipe(response.data)
                        }
                        is Resource.Error -> {
                            binding.animLoading.gone()
                            Log.e(
                                "throwable-recipe",
                                response.throwable.toString()
                            )
                        }
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

    private fun loadRecipe(recipe: RecipeUI) {
        with(recipe) {
            toolbar(title)
            foodImage(image)
            initializeView(
                recipe
            )

            with(binding) {
                gluten.visibleOrGone(glutenFree)
                vegan.visibleOrGone(vegetarian)
                lactose.visibleOrGone(dairyFree)
                cheap.visibleOrGone(recipe.cheap)
                popularity.visibleOrGone(veryPopular)
                healthy.visibleOrGone(veryHealthy)

            }

        }
    }

    private fun foodImage(url: String) {
        binding.foodImage.glideImage(url)
    }

    private fun toolbar(foodName: String) {
        with(binding) {

            toolbar.title = foodName
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.saveIcon -> {
                        binding.toolbar.menu.getItem(0).setIcon(R.drawable.star_on)
                        true
                    }
                    else -> false
                }
            }
        }

    }


    private fun initializeView(
        recipe: RecipeUI,

        ) {
        val tabLayoutName = listOf("Recipe", "Ingredient")
        with(binding) {
            viewPager.adapter =
                ViewPagerAdapter(childFragmentManager, lifecycle, recipe)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabLayoutName[position]
            }.attach()

        }
    }

}