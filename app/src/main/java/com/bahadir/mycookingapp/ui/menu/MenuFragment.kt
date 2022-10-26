package com.bahadir.mycookingapp.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.GridLayoutManager
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.command.Resource
import com.bahadir.mycookingapp.databinding.FragmentMenuBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val binding by viewBinding(FragmentMenuBinding::bind)
    private val adapter: MenuCategoryItemAdapter by lazy { MenuCategoryItemAdapter() }
    private val viewModel: MenuCategoryItemViewModel by viewModels()
    //private val args: MenuFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding()
        collectData()
        //viewModel.getMenuCategoryItem(20, "soup")
    }

    private fun binding() {
        with(binding) {
            recyclerMenu.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerMenu.adapter = adapter
        }
    }

    private fun collectData() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                menuCategoryItem.collect { response ->
                    when (response) {
                        is Resource.Loading -> Log.e("loading", "loading")
                        is Resource.Success -> {
                            adapter.submitList(response.data)
                        }
                        is Resource.Error -> Log.e("throwable", response.throwable.toString())
                    }
                }
            }
        }

    }
}