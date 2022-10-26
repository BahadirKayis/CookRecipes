package com.bahadir.mycookingapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.databinding.FragmentHomeBinding

import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomFoodFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding()
        collectData()

    }

    private fun binding() {
       with(binding) {
           recyclerMenu.layoutManager = GridLayoutManager(requireContext(),2)
       }
    }

    private fun collectData() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                randomPopularity.collect { response ->
                    when (response) {
                        is Resource.Loading -> Log.e("loading", "loading")
                        is Resource.Success -> {
                            Log.e("data", response.data.toString())
                            val adapter=PopularityAdapter(response.data)
                            binding.recyclerPopularity.adapter = adapter
                        }
                        is Resource.Error -> Log.e("throwable", response.throwable.toString())
                    }
                }

            }

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                menu.collect{ response ->
                    when (response) {
                        is Resource.Loading -> Log.e("loading", "loading")
                        is Resource.Success -> {
                            Log.e("data", response.data.toString())
                            val adapter=MenuAdapter(response.data)
                            binding.recyclerMenu.adapter = adapter
                        }
                        is Resource.Error -> Log.e("throwable", response.throwable.toString())
                    }
                }

            }

        }
    }
}