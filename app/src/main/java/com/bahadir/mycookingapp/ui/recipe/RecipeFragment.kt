package com.bahadir.mycookingapp.ui.recipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.ClickToAny
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.common.extensions.*
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.databinding.FragmentRecipeBinding
import com.bahadir.mycookingapp.domain.model.RecipeUI
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class RecipeFragment : Fragment(R.layout.fragment_recipe),
    ClickToAny {
    private val binding by viewBinding(FragmentRecipeBinding::bind)
    private val viewModel: RecipeViewModel by viewModels()
    private val args: RecipeFragmentArgs by navArgs()
    private var isTheRecipeSaved: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectData()
    }

    private fun initUI(recipe: RecipeUI) {
        with(binding) {
            appbar.addOnOffsetChangedListener { _, verticalOffset ->
                if (abs(verticalOffset) >= appbar.totalScrollRange) {
                    //Collapsed
                    collapsingToolbar.title = recipe.title
                    toolbar.visible()
                } else {
                    //Expanded
                    toolbar.gone()
                    collapsingToolbar.title = ""
                }
            }
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
                    R.id.shareOtherApp -> {
                        shareOtherApp(
                            recipe.title, recipe.sourceUrl
                        )
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun collectData() {
        with(viewModel) {
            recipe.collectInStarted(viewLifecycleOwner) { response ->
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
                            "throwable-recipe", response.throwable.toString()
                        )
                    }
                }
            }

            similarRecipe.collectInStarted(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val adapter = SimilarRecipeAdapter(response.data, this@RecipeFragment)
                        binding.similarRecipe.adapter = adapter
                    }
                    is Resource.Error -> Log.e(
                        "throwable-similarRecipe", response.throwable.toString()
                    )
                }
            }

            isSavedRecipe.collectInStarted(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        if (response.data) {
                            isTheRecipeSaved = true
                            binding.toolbar.menu.getItem(0).setIcon(R.drawable.star_on)
                        } else {
                            isTheRecipeSaved = false
                            binding.toolbar.menu.getItem(0).setIcon(R.drawable.star_off)
                        }
                    }
                    is Resource.Error -> {
                        Log.e("throwable-isSavedRecipe", response.throwable.toString())
                    }
                }
            }
        }
    }

    private fun loadRecipe(recipe: RecipeUI) {
        with(recipe) {
            initUI(this)
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

    private fun initializeView(
        recipe: RecipeUI
    ) {
        val tabLayoutName = listOf("Recipe", "Ingredient")
        with(binding) {
            viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle, recipe)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabLayoutName[position]
            }.attach()

        }
    }

    private fun shareOtherApp(foodName: String, url: String) {
        Log.i("shareOtherApp", "$foodName $url")
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TITLE, foodName)
        intent.putExtra(Intent.EXTRA_TEXT, url)
        startActivity(Intent.createChooser(intent, "Share"))
    }

    override fun onClickToAny(id: Int?, title: String?) {
        id?.let {
            findNavController().navigate(RecipeFragmentDirections.actionRecipeFragmentSelf(it))
        }
    }

}