package com.bahadir.mycookingapp.ui.recipe.recipeismade

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.databinding.ItemRecipeViewpagerBinding

import com.bahadir.mycookingapp.domain.model.StepUI

class StepAdapter(private val step: List<StepUI>) :
    RecyclerView.Adapter<StepAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRecipeViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(step: StepUI) {
            binding.itemName.text = step.step
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecipeViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(step[position])

    override fun getItemCount(): Int = step.size
}