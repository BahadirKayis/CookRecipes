package com.bahadir.mycookingapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.common.gone
import com.bahadir.mycookingapp.common.viewBinding

import com.bahadir.mycookingapp.common.visible
import com.bahadir.mycookingapp.databinding.FragmentHomeBinding


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), MenuAdapter.MenuAdapterInterface,
    RandomAdapter.RandomAdapterInterface {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding()
        collectData()

    }

    private fun binding() {
        with(binding) {
            recyclerMenu.layoutManager = GridLayoutManager(requireContext(), 2)

            showMoreBreakfast.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionRandomFoodFragmentToMenuFragment(
                        ""
                    )
                )
            }
        }
    }

    private fun collectData() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                randomPopularity.collect { response ->
                    when (response) {
                        is Resource.Loading -> binding.animLoading.visible()
                        is Resource.Success -> {
                            val adapter = RandomAdapter(response.data, this@HomeFragment)
                            binding.recyclerPopularity.adapter = adapter
                            binding.animLoading.gone()
                        }
                        is Resource.Error -> {
                            binding.animLoading.gone()
                            Log.e("throwable", response.throwable.toString())
                        }
                    }
                }

            }

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                menu.collect { response ->
                    when (response) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {

                            val adapter = MenuAdapter(response.data, this@HomeFragment)
                            binding.recyclerMenu.adapter = adapter
                        }
                        is Resource.Error -> {

                            Log.e("throwable", response.throwable.toString())
                        }
                    }
                }

            }

        }
    }

    override fun menuToCategories(category: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionRandomFoodFragmentToMenuFragment(category)
        )
    }

    override fun randomToRecipe(id: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionRandomFoodFragmentToRecipeFragment(
                id
            )
        )
    }
}