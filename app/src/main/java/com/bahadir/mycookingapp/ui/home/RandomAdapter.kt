package com.bahadir.mycookingapp.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.common.ClickToAny
import com.bahadir.mycookingapp.common.glideImage
import com.bahadir.mycookingapp.common.titleCount
import com.bahadir.mycookingapp.databinding.ItemRandomRecipeBinding
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI


class RandomAdapter(
    private val food: List<RandomFoodRecipeUI>,
    private val randomAdapter: ClickToAny
) :
    RecyclerView.Adapter<RandomAdapter.RandomFoodViewHolder>() {
    inner class RandomFoodViewHolder(private val binding: ItemRandomRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RandomFoodRecipeUI) {
            with(binding) {
                with(item) {

                    foodImage.glideImage(image)
                    binding.title.text = title.titleCount(20)
                    itemView.setOnClickListener {
                        randomAdapter.onClickToAny(id = id)
                    }

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomFoodViewHolder {
        val binding =
            ItemRandomRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RandomFoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RandomFoodViewHolder, position: Int) {
        holder.bind(food[position])
    }

    override fun getItemCount(): Int {
        return food.size
    }


}