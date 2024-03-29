package com.bahadir.mycookingapp.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.ClickToAny
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.common.extensions.collectInStarted
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.databinding.FragmentFavoriteBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite), ClickToAny {
    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel: FavoriteViewModel by viewModels()
    private val adapter: FavoriteAdapter by lazy { FavoriteAdapter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteRecipes()
        collectData()
        initUI()
    }

    private fun collectData() {
        with(viewModel) {
            getAllRecipe.collectInStarted(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        Log.i("TAG:", "Loading")
                    }
                    is Resource.Success -> {
                        adapter.submitList(response.data)
                    }
                    is Resource.Error -> {
                        Log.e(
                            "throwable-recipe", response.throwable.toString()
                        )
                    }
                }
            }
            tasksEvent.collectInStarted(viewLifecycleOwner) { event ->
                when (event) {
                    is FavoriteViewModel.RecipeEvent.ShowUndoDeleteRecipeMessage -> {
                        Snackbar.make(requireView(), "Recipe Deleted", 3000).setAction("UNDO") {
                            viewModel.onUndoRecipe(event.recipe)
                        }.show()
                    }
                }
            }
        }
    }

    private fun initUI() {
        binding.recipeRecycler.adapter = adapter
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val task = adapter.currentList[viewHolder.layoutPosition]
                viewModel.deleteRecipe(task)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recipeRecycler)
    }

    override fun onClickToAny(id: Int?, title: String?) {
        id?.let {
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToRecipeFragment(it)
            )
        }
    }
}


