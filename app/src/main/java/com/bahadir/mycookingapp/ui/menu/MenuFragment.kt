package com.bahadir.mycookingapp.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.Resource
import com.bahadir.mycookingapp.common.gone
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.common.visible
import com.bahadir.mycookingapp.databinding.FragmentMenuBinding


import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu),
    MenuCategoryItemAdapter.MenuCategoryInterface {
    private val binding by viewBinding(FragmentMenuBinding::bind)
    private val adapter: MenuCategoryItemAdapter by lazy { MenuCategoryItemAdapter(this) }
    private val viewModel: MenuCategoryItemViewModel by viewModels()
    private val args: MenuFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categoryName = args.categoryName
        binding()
        collectData()
        getCategoryItem(categoryName)

        categoryName.also {
            if (it == "") {
                "Random Recipe".also { its -> binding.categoryName.text = its }
            } else {
                "$it Recipe".also { its -> binding.categoryName.text = its }
            }


        }
    }

    private fun getCategoryItem(categoryName: String) {
        viewModel.getMenuCategoryItem(20, categoryName.lowercase(Locale.getDefault()))

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
                        is Resource.Loading -> binding.animLoading.visible()
                        is Resource.Success -> {
                            binding.animLoading.gone()
                            adapter.submitList(response.data)
                        }
                        is Resource.Error -> {
                            binding.animLoading.gone()
                            Log.e("throwable", response.throwable.toString())
                        }
                    }
                }
            }
        }

    }

    override fun menuCategoryToRecipe(id: Int) {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToRecipeFragment(id))
    }
}