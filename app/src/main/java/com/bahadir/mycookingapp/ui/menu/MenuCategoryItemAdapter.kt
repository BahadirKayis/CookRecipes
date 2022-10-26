package com.bahadir.mycookingapp.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
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
                        .error(R.drawable.ic_baseline_no_photography_24)
                        .into(foodImage)

                    title.text = item.title
                    readyMinutes.text= "${item.readyInMinutes} mn"


                }

            } catch (e: Exception) {

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