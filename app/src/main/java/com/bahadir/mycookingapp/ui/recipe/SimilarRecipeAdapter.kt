package com.bahadir.mycookingapp.ui.recipe


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bahadir.mycookingapp.common.glideImage
import com.bahadir.mycookingapp.common.titleCount
import com.bahadir.mycookingapp.databinding.ItemRandomFoodBinding
import com.bahadir.mycookingapp.domain.model.SimilarRecipeUI


class SimilarRecipeAdapter(
    private val food: List<SimilarRecipeUI>,
    private val recipeInterface: SimilarRecipeAdapterInterface
) :
    RecyclerView.Adapter<SimilarRecipeAdapter.RandomFoodViewHolder>() {
    inner class RandomFoodViewHolder(private val binding: ItemRandomFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimilarRecipeUI) {
            with(binding) {
                foodImage.glideImage(item.image!!)
                title.text = item.title.titleCount(20)
                itemView.setOnClickListener {
                    recipeInterface.similarRecipeClick(item.id)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomFoodViewHolder {
        val binding =
            ItemRandomFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RandomFoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RandomFoodViewHolder, position: Int) {
        holder.bind(food[position])
    }

    override fun getItemCount(): Int {
        return food.size
    }

    interface SimilarRecipeAdapterInterface {
        fun similarRecipeClick(recipeId: Int)
    }
}