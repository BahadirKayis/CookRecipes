package com.bahadir.mycookingapp.ui.home


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.*
import com.bahadir.mycookingapp.data.model.local.CustomData
import com.bahadir.mycookingapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ClickToAny {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        collectData()

    }

    private fun initUI() {
        with(binding) {
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

            val adapter = MenuAdapter(CustomData.getMenu(), this@HomeFragment)
            binding.recyclerMenu.adapter = adapter

        }
    }


    override fun onClickToAny(id: Int?, title: String?) {
        title?.let {
            findNavController().navigate(
                HomeFragmentDirections.actionRandomFoodFragmentToMenuFragment(it)
            )
        } ?: run {
            id?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionRandomFoodFragmentToRecipeFragment(
                        it
                    )
                )
            }
        }

    }
}