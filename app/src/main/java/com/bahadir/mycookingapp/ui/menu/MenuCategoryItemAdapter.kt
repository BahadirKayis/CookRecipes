package com.bahadir.mycookingapp.ui.menu

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.databinding.MenuCategoryItemBinding
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MenuCategoryItemAdapter :
    ListAdapter<RandomFoodRecipeUI, MenuCategoryItemAdapter.MenuViewHolder>(MenuCategoryItemCallBack()) {
    inner class MenuViewHolder(private val binding: MenuCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RandomFoodRecipeUI) {
            try {
                with(item) {
                    with(binding) {

                        val circularProgressDrawable = CircularProgressDrawable(foodImage.context)
                        circularProgressDrawable.strokeWidth = 5f
                        circularProgressDrawable.centerRadius = 30f
                        circularProgressDrawable.start()

                        Glide.with(foodImage.context)
                            .load(image)
                            .override(500, 500) //1
                            .diskCacheStrategy(DiskCacheStrategy.DATA) //6
                            .placeholder(circularProgressDrawable)
                            .error(R.drawable.ic_baseline_no_photography_24)
                            .into(foodImage)

                        title.text = item.title

                        when (healthScore) {
                            in 1..24 -> ratingBar.rating = 1f
                            in 25..39 -> ratingBar.rating = 1.5f
                            in 40..44 -> ratingBar.rating = 2f
                            in 45..59 -> ratingBar.rating = 2.5f
                            in 60..64 -> ratingBar.rating = 3f
                            in 65..79 -> ratingBar.rating = 3.5f
                            in 80..84 -> ratingBar.rating = 4f
                            in 85..99 -> ratingBar.rating = 4.5f
                            else -> ratingBar.rating = 5f
                        }


                    }
                }

                

            } catch (e: Exception) {
                Log.e("bind-Ex", e.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding =
            MenuCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(getItem(position))

    }


    class MenuCategoryItemCallBack : DiffUtil.ItemCallback<RandomFoodRecipeUI>() {
        override fun areItemsTheSame(
            oldItem: RandomFoodRecipeUI, newItem: RandomFoodRecipeUI
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RandomFoodRecipeUI, newItem: RandomFoodRecipeUI
        ): Boolean {
            return oldItem == newItem
        }
    }

}