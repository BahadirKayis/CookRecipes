package com.bahadir.mycookingapp.ui.filter

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.extensions.gone
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.data.model.local.CustomData
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FilterFragment : BottomSheetDialogFragment(R.layout.fragment_filter) {
    private val binding by viewBinding(FragmentFilterBinding::bind)
    private lateinit var adapter: FilterTypeAdapter
    private val spanCount: Int = 3
    private lateinit var returnFilterModel: Filter
    private val args: FilterFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        returnFilterModel = args.filterModel
        initUI()
        clickListener()
    }

    private fun clickListener() {
        with(binding) {
            applyButton.setOnClickListener {
                setFragmentResult("popUp", bundleOf("filterList" to returnFilterModel))
                findNavController().popBackStack()
            }
            filterClear.setOnClickListener {
                returnFilterModel = CustomData.getFilterModel()
                initUI()
            }
            closeButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initUI() {
        with(binding)
        {
            adapter = FilterTypeAdapter(returnFilterModel.diet).also {
                dietRecyclerView.adapter = it
                dietRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
                it.listHigh = { highDiet ->
                    returnFilterModel.diet = highDiet
                }
            }

            adapter = FilterTypeAdapter(returnFilterModel.country).also {
                countryRecyclerView.adapter = it
                countryRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
                it.listHigh = { highDiet ->
                    returnFilterModel.country = highDiet
                }
            }
            adapter = FilterTypeAdapter(returnFilterModel.intolerances).also {
                intoleranceRecyclerView.adapter = it
                intoleranceRecyclerView.layoutManager =
                    GridLayoutManager(requireContext(), spanCount)
                it.listHigh = { highDiet ->
                    returnFilterModel.intolerances = highDiet
                }
            }

            returnFilterModel.mealTypes?.let {
                adapter = FilterTypeAdapter(it)
                mealTypeRecyclerView.adapter = adapter
                mealTypeRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
                adapter.listHigh = { highMealType ->
                    returnFilterModel.mealTypes = highMealType
                }
            } ?: run {
                mealTypeText.gone()
                mealTypeRecyclerView.gone()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { layout ->
                val behaviour = BottomSheetBehavior.from(layout)
                setupFullHeight(layout)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                behaviour.skipCollapsed = true
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

}