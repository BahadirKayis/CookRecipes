package com.bahadir.mycookingapp.ui.search

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.*
import com.bahadir.mycookingapp.data.mapper.randomToSearchResultUI
import com.bahadir.mycookingapp.data.model.local.CustomData
import com.bahadir.mycookingapp.data.model.remote.filter.Filter
import com.bahadir.mycookingapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), SearchAdapter.SearchAdapterInterface {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private var filterModel: Filter? = null

    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
        filterResult()
        collectData()
        editTextEvent()

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

    private fun clickListener() {
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
                                searchRecyclerView.visible()
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
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    getRandom.collect { response ->
                        when (response) {
                            is Resource.Loading -> binding.animLoading.visible()
                            is Resource.Success -> {
                                val adapter = SearchAdapter(
                                    response.data.randomToSearchResultUI(),
                                    this@SearchFragment
                                )
                                binding.searchRecyclerView.adapter = adapter
                                binding.animLoading.gone()
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

    }

    private fun editTextEvent() {
        with(binding) {
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
                    KeyEvent.KEYCODE_BACK -> {
                        searchEditText.clearFocus()
                        searchEditText.hideKeyboard()
                        cancelSearch.gone()
                        true
                    }
                    else -> false
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


