package com.bahadir.mycookingapp.ui.menu


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.viewBinding
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
        collectData(categoryName)
        categoryName.also {
            if (it == "") {
                "Random Recipe".also { its -> binding.categoryName.text = its }
            } else {
                "$it Recipe".also { its -> binding.categoryName.text = its }
            }
        }
    }

    private fun binding() {
        with(binding) {
            recyclerMenu.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerMenu.adapter = adapter
        }
    }


    private fun collectData(categoryName: String) {
        with(viewModel) {

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                getMenuCategoryItem(
                    5,
                    categoryName.lowercase(Locale.getDefault())
                ).collect { pagingData ->
                    adapter.submitData(pagingData)

                }
            }

        }

    }

    override fun menuCategoryToRecipe(id: Int) {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToRecipeFragment(id))
    }
}