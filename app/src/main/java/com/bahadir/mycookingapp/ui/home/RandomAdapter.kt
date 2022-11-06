package com.bahadir.mycookingapp.ui.home


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.common.glideImage
import com.bahadir.mycookingapp.common.titleCount
import com.bahadir.mycookingapp.databinding.ItemRandomFoodBinding
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI


class RandomAdapter(
    private val food: List<RandomFoodRecipeUI>,
    private val randomAdapter: RandomAdapterInterface
) :
    RecyclerView.Adapter<RandomAdapter.RandomFoodViewHolder>() {
    inner class RandomFoodViewHolder(private val binding: ItemRandomFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RandomFoodRecipeUI) {
            with(binding) {
                with(item) {


                    foodImage.glideImage(image)
                    binding.title.text = title.titleCount()

                    itemView.setOnClickListener {
                        Log.i("randomAdapter", id.toString())
                        randomAdapter.randomToRecipe(id)

                    }
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

    interface RandomAdapterInterface {
        fun randomToRecipe(id: Int)
    }
}