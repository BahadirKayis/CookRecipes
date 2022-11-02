package com.bahadir.mycookingapp.ui.recipe


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bahadir.mycookingapp.common.glideImage
import com.bahadir.mycookingapp.common.titleCount
import com.bahadir.mycookingapp.databinding.RandomFoodItemBinding
import com.bahadir.mycookingapp.domain.model.SimilarRecipeUI


class SimilarRecipeAdapter(private val food: List<SimilarRecipeUI>) :
    RecyclerView.Adapter<SimilarRecipeAdapter.RandomFoodViewHolder>() {
    inner class RandomFoodViewHolder(private val binding: RandomFoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimilarRecipeUI) {
            with(binding) {
                foodImage.glideImage(item.image!!)
                title.text = item.title.titleCount()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomFoodViewHolder {
        val binding =
            RandomFoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RandomFoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RandomFoodViewHolder, position: Int) {
        holder.bind(food[position])
    }

    override fun getItemCount(): Int {
        return food.size
    }


}