package com.bahadir.mycookingapp.ui.randomfood

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.databinding.FragmentRandomFoodBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomFoodFragment : Fragment(R.layout.fragment_random_food) {
    private val binding by viewBinding(FragmentRandomFoodBinding::bind)
    private val viewModel: RandomViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding()
        collectData()

    }

    private fun binding() {
        with(binding) {
            recyclerBreakfast.apply {
                layoutManager = LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }
            recyclerSoup.apply {
                layoutManager = LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }
            recyclerMainCourse.apply {
                layoutManager = LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }
        }
    }

    private fun collectData() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                randomBreakfast.collect { response ->
                    when (response) {
                        is Resource.Loading -> Log.e("loading", "loading")
                        is Resource.Success -> {
                            Log.e("data", response.data.toString())
                            val adapter=RandomFoodAdapter(response.data)
                            binding.recyclerBreakfast.adapter = adapter
                        }
                        is Resource.Error -> Log.e("throwable", response.throwable.toString())
                    }
                }

            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                randomSoup.collect { response ->
                    when (response) {
                        is Resource.Loading -> Log.e("loading", "loading")
                        is Resource.Success -> {
                            Log.e("data", response.data.toString())
                            val adapter=RandomFoodAdapter(response.data)
                            binding.recyclerSoup.adapter = adapter
                        }
                        is Resource.Error -> Log.e("throwable", response.throwable.toString())
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                randomMainCourse.collect { response ->
                    when (response) {
                        is Resource.Loading -> Log.e("loading", "loading")
                        is Resource.Success -> {
                            Log.e("data", response.data.toString())
                            val adapter=RandomFoodAdapter(response.data)

                            binding.recyclerMainCourse.adapter = adapter
                        }
                        is Resource.Error -> Log.e("throwable", response.throwable.toString())
                    }
                }
            }
        }
    }
}