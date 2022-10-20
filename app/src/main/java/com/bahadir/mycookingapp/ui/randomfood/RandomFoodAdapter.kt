package com.bahadir.mycookingapp.ui.randomfood


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.databinding.RandomFoodItemBinding
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class RandomFoodAdapter(private val food: List<RandomFoodRecipeUI>) : RecyclerView.Adapter<RandomFoodAdapter.RandomFoodViewHolder>() {
    inner class RandomFoodViewHolder(private val binding: RandomFoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RandomFoodRecipeUI) {
            with(binding) {
                val circularProgressDrawable = CircularProgressDrawable(foodImage.context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                Glide.with(foodImage.context)
                    .load(item.image)
                    .override(500, 500) //1
                    .diskCacheStrategy(DiskCacheStrategy.DATA) //6
                    .placeholder(circularProgressDrawable)
                    .error(R.drawable.ic_launcher_background)
                    .into(foodImage)
                title.text = item.title
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