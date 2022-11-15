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
import com.bahadir.mycookingapp.common.viewBinding
import com.bahadir.mycookingapp.data.model.filter.Filter
import com.bahadir.mycookingapp.data.model.local.CustomData
import com.bahadir.mycookingapp.databinding.FragmentFilterBinding
import com.bahadir.mycookingapp.ui.filter.adapter.CountryAdapter
import com.bahadir.mycookingapp.ui.filter.adapter.DietsAdapter
import com.bahadir.mycookingapp.ui.filter.adapter.IntolerancesAdapter
import com.bahadir.mycookingapp.ui.filter.adapter.MealTypeAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FilterFragment : BottomSheetDialogFragment(R.layout.fragment_filter) {
    private val binding by viewBinding(FragmentFilterBinding::bind)
    private lateinit var adapterDiet: DietsAdapter
    private lateinit var adapterCountry: CountryAdapter
    private lateinit var adapterIntolerance: IntolerancesAdapter
    private lateinit var adapterMealType: MealTypeAdapter
    private val spanCount: Int = 3
    private lateinit var returnFilterModel: Filter
    private val args: FilterFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        returnFilterModel = args.filterModel
        initUi()
        returnDataList()
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
                initUi()
            }
            closeButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initUi() {

        with(binding)
        {
            adapterDiet = DietsAdapter(returnFilterModel.diet)
            dietRecyclerView.adapter = adapterDiet
            dietRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)

            adapterCountry = CountryAdapter(returnFilterModel.country)
            countryRecyclerView.adapter = adapterCountry
            countryRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)

            adapterIntolerance = IntolerancesAdapter(returnFilterModel.intolerances)
            intoleranceRecyclerView.adapter = adapterIntolerance
            intoleranceRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)

            adapterMealType = MealTypeAdapter(returnFilterModel.mealTypes)
            mealTypeRecyclerView.adapter = adapterMealType
            mealTypeRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)

        }

    }

    private fun returnDataList() {
        adapterDiet.listHigh = { highDiet ->
            returnFilterModel.diet = highDiet

        }
        adapterCountry.listHigh = { highCountry ->
            returnFilterModel.country = highCountry

        }
        adapterIntolerance.listHigh = { highIntolerance ->
            returnFilterModel.intolerances = highIntolerance

        }
        adapterMealType.listHigh = { highMealType ->
            returnFilterModel.mealTypes = highMealType

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