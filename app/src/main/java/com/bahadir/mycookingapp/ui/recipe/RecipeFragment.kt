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
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeFragment : Fragment(R.layout.fragment_recipe),
    SimilarRecipeAdapter.SimilarRecipeAdapterInterface {
    private val binding by viewBinding(FragmentRecipeBinding::bind)
    private val viewModel: RecipeViewModel by viewModels()
    private val args: RecipeFragmentArgs by navArgs()
    private var isTheRecipeSaved: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isRecipeSaved(args.recipeId)
        cloudRequest()
        collectData()
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
                            Log.i("recs", response.data.toString())
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
                            val adapter = SimilarRecipeAdapter(response.data, this@RecipeFragment)
                            binding.similarRecipe.adapter = adapter
                        }
                        is Resource.Error -> Log.e(
                            "throwable-similarRecipe",
                            response.throwable.toString()
                        )
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.isSavedRecipe.collect { response ->
                    when (response) {

                        is Resource.Loading -> {
                            Log.e("collectDataİs-", "loading")
                        }

                        is Resource.Success -> {
                            Log.e("collectDataİs-", response.data.toString())
                            if (response.data) {
                                isTheRecipeSaved = true
                                binding.toolbar.menu.getItem(0)
                                    .setIcon(R.drawable.star_on)
                            } else {
                                isTheRecipeSaved = false
                                binding.toolbar.menu.getItem(0)
                                    .setIcon(R.drawable.star_off)
                            }
                        }
                        is Resource.Error -> {
                            Log.e("collectDataİs-", "error")
                        }
                    }


                }
            }


        }
    }

    private fun loadRecipe(recipe: RecipeUI) {
        with(recipe) {
            toolbar(title, this)
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

    private fun toolbar(foodName: String, recipe: RecipeUI) {
        with(binding) {

            toolbar.title = foodName
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.saveIcon -> {
                        isTheRecipeSaved = when (isTheRecipeSaved) {
                            true -> {
                                viewModel.deleteRecipe(args.recipeId)
                                binding.toolbar.menu.getItem(0).setIcon(R.drawable.star_off)
                                false
                            }
                            false -> {
                                viewModel.addRecipe(recipe)
                                binding.toolbar.menu.getItem(0).setIcon(R.drawable.star_on)
                                true
                            }
                        }

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

    override fun similarRecipeClick(recipeId: Int) {
        findNavController().navigate(RecipeFragmentDirections.actionRecipeFragmentSelf(recipeId))
    }

}