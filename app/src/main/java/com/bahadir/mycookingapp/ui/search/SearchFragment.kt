package com.bahadir.mycookingapp.ui.search

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.*
import com.bahadir.mycookingapp.data.model.local.CustomData
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), SearchAdapter.SearchAdapterInterface {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private var filterModel: Filter? = null

    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        filterResult()
        collectData()


    }

    private fun filterResult() {
        setFragmentResultListener("popUp") { _, bundle ->
            filterModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("filterList", Filter::class.java)
            } else {

                @Suppress("DEPRECATION") bundle.getParcelable("filterList")
            }
            viewModel.getSearch(
                query = binding.searchEditText.text.toString(), filter = filterModelController()
            )
        }
    }

    private fun filterModelController(): Filter {
        return filterModel?.let {
            filterModel
        } ?: run {
            CustomData.getFilterModel(true)
        }
    }

    private fun initUI() {
        with(binding) {
            filterButton.setOnClickListener {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToFilterFragment(
                        filterModelController()
                    )
                )
            }

            searchImageView.setOnClickListener {

                viewModel.getSearch(
                    query = searchEditText.text.toString(), filter = filterModelController()
                )
            }
            cancelSearch.setOnClickListener {
                searchEditText.clearFocus()
                requireView().hideKeyboard()
                searchEditText.text?.clear()
                cancelSearch.gone()
            }

            searchEditText.setOnClickListener {
                cancelSearch.visible()
            }

            searchEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    cancelSearch.visible()
                } else {
                    requireView().hideKeyboard()
                    cancelSearch.gone()
                }
            }
            searchEditText.setOnKeyListener { _, keyCode, _ ->
                when (keyCode) {
                    KeyEvent.KEYCODE_ENTER -> {
                        viewModel.getSearch(
                            query = searchEditText.text.toString(), filter = filterModelController()
                        )
                        searchEditText.clearFocus()
                        searchEditText.hideKeyboard()
                        cancelSearch.gone()
                        true
                    }

                    else -> false
                }
            }
            KeyboardVisibilityEvent.setEventListener(requireActivity()) { isOpen ->
                if (!isOpen) {
                    searchEditText.clearFocus()
                    searchEditText.hideKeyboard()
                    cancelSearch.gone()
                }
            }

        }

    }

    private fun collectData() {
        with(viewModel) {
            with(binding) {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    getSearch.collect {
                        when (it) {
                            is Resource.Success -> {
                                animLoading.gone()
                                errorTextView.gone()
                                searchRecyclerView.adapter =
                                    SearchAdapter(it.data, this@SearchFragment)
                            }
                            is Resource.Error -> {
                                animLoading.gone()
                                searchRecyclerView.gone()
                                errorTextView.visible()
                                errorTextView.text = it.throwable.toString()
                            }
                            is Resource.Loading -> {
                                animLoading.visible()
                                errorTextView.gone()
                            }
                        }
                    }
                }


            }

        }

    }


    override fun searchToRecipe(id: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToRecipeFragment(
                id
            )
        )
    }


}


