package com.bahadir.mycookingapp.ui.menu


import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.ClickToAny
import com.bahadir.mycookingapp.common.gone
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.data.model.local.CustomData
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu),
    ClickToAny {
    private val binding by viewBinding(FragmentMenuBinding::bind)
    private val adapter: MenuCategoryItemAdapter by lazy { MenuCategoryItemAdapter(this) }
    private val viewModel: MenuCategoryItemViewModel by viewModels()
    private val args: MenuFragmentArgs by navArgs()
    private var filterModel: Filter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categoryName = args.categoryName
        collectData()
        filterResult()
        initUI()
        categoryName.also {
            if (it == "") {
                "Random Recipe".also { its -> binding.categoryName.text = its }
            } else {
                "$it Recipe".also { its -> binding.categoryName.text = its }
            }
        }

    }

    private fun filterResult() {
        setFragmentResultListener("popUp") { _, bundle ->
            filterModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("filterList", Filter::class.java)
            } else {

                @Suppress("DEPRECATION") bundle.getParcelable("filterList")
            }
            viewModel.getMenuCategoryItem(filterModelController())
        }
    }


    private fun initUI() {
        with(binding) {
            filterButton.setOnClickListener {
                findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToFilterFragment(
                        filterModelController()
                    )
                )
            }
        }
    }

    private fun collectData() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                getMenu.collect {
                    binding.animLoading.gone()
                    binding.recyclerMenu.adapter = adapter
                    adapter.submitData(lifecycle, it)
                }

            }

        }

    }

    private fun filterModelController(): Filter {
        return filterModel?.let {
            filterModel
        } ?: run {
            CustomData.getFilterModel(false)
        }
    }


    override fun onClickToAny(id: Int?, title: String?) {
        id?.let {
            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToRecipeFragment(
                    it
                )
            )
        }
    }
}