package com.bahadir.mycookingapp.ui.recipe.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.databinding.RecipeViewpagerItemBinding
import com.bahadir.mycookingapp.domain.model.IngredientUI

class IngredientAdapter(private val ingredient: List<IngredientUI>) :
    RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RecipeViewpagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IngredientUI) {
            with(binding) {
                itemName.text = item.name

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecipeViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(ingredient[position])

    override fun getItemCount(): Int {
        return ingredient.size
    }

}