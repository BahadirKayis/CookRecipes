package com.bahadir.mycookingapp.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.databinding.FragmentFavoriteBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel: FavoriteViewModel by viewModels()
    private val adapter: FavoriteAdapter by lazy { FavoriteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectData()
        viewModel.getAllRecipe()
        setUpRecyclerView()
    }

    private fun collectData() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                getAllRecipe.collect { response ->
                    when (response) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {

                            adapter.submitList(response.data)
                            binding.recipeRecycler.adapter = adapter
                            binding.recipeRecycler.setHasFixedSize(true)

                        }
                        is Resource.Error -> {

                            Log.e(
                                "throwable-recipe",
                                response.throwable.toString()
                            )
                        }
                    }

                }
            }
        }
    }

    private fun setUpRecyclerView() {


        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val task = adapter.currentList[viewHolder.bindingAdapterPosition]
                viewModel.deleteRecipe(task)
            }

//            override fun onChildDraw(
//                c: Canvas,
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                dX: Float,
//                dY: Float,
//                actionState: Int,
//                isCurrentlyActive: Boolean
//            ) {
//                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(R.color.salad))
//                Recycler.Builder(
//                    c,
//                    recyclerView,
//                    viewHolder,
//                    dX,
//                    dY,
//                    actionState,
//                    isCurrentlyActive
//                )
//                    .addBackgroundColor(
//                        ContextCompat.getColor(
//                            requireContext(),
//                            android.R.color.holo_red_light
//                        )
//                    )
//                    .addActionIcon(R.drawable.ic_remove_item)
//                    .addSwipeRightLabel("Deleting")
//                    .setSwipeRightLabelColor(R.color.white)
//                    .addSwipeLeftLabel("Deleting")
//                    .setSwipeRightLabelColor(R.color.white)
//                    .setSwipeLeftLabelColor(R.color.white)
//                    .create()
//                    .decorate()
//
//                super.onChildDraw(
//                    c,
//                    recyclerView,
//                    viewHolder,
//                    dX,
//                    dY,
//                    actionState,
//                    isCurrentlyActive
//                )

            //}
        }).attachToRecyclerView(binding.recipeRecycler)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.tasksEvent.collect { event ->
                when (event) {
                    is FavoriteViewModel.TasksEvent.ShowUndoDeleteTaskMessage -> {
                        Snackbar.make(requireView(), "Recipe Deleted", 3000).setAction("UNDO") {
                            viewModel.onUndoRecipe(event.recipe)
                        }.show()
                    }
                }

            }
        }
    }

//        val callback: ItemTouchHelper.SimpleCallback = object :
//            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                adapter.deleteItem(viewHolder.adapterPosition)
//            }
//
//            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
//
//                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                    .addBackgroundColor(
//                        ContextCompat.getColor(
//                            this@ListActivity,
//                            android.R.color.holo_red_light
//                        )
//                    )
//                    .addActionIcon(R.drawable.ic_baseline_delete_sweep_24)
//                    .addSwipeRightLabel("Deleting the Item")
//                    .addSwipeLeftLabel("Deleting the Item")
//                    .setSwipeRightLabelColor(R.color.white)
//                    .setSwipeLeftLabelColor(R.color.white)
//                    .create()
//                    .decorate()
//
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//            }
//        }
//        val itemTouchHelper = ItemTouchHelper(callback)
//        itemTouchHelper.attachToRecyclerView(rv)
//    }
}