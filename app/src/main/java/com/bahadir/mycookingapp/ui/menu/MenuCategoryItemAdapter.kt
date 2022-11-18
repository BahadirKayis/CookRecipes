package com.bahadir.mycookingapp.ui.menu

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.glideImage
import com.bahadir.mycookingapp.databinding.ItemMenuCategoryListBinding
import com.bahadir.mycookingapp.domain.model.RandomFoodRecipeUI


class MenuCategoryItemAdapter(
    private val menuCategoryInterface: MenuCategoryInterface
) :
    PagingDataAdapter<RandomFoodRecipeUI, MenuCategoryItemAdapter.ViewHolder>(diffCallBack) {

    inner class ViewHolder(private val binding: ItemMenuCategoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RandomFoodRecipeUI) {
            try {
                with(item) {
                    with(binding) {
                        itemView.animation =
                            AnimationUtils.loadAnimation(
                                itemView.context,
                                R.anim.menu_category_item_scale
                            )

                        foodImage.glideImage(item.image!!)
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

                        itemView.setOnClickListener {
                            menuCategoryInterface.menuCategoryToRecipe(id)
                        }
                    }
                }


            } catch (e: Exception) {
                Log.e("bind-Ex", e.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMenuCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

    }


    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<RandomFoodRecipeUI>() {
            override fun areItemsTheSame(
                oldItem: RandomFoodRecipeUI, newItem: RandomFoodRecipeUI
            ): Boolean {
                return newItem == oldItem
            }

            override fun areContentsTheSame(
                oldItem: RandomFoodRecipeUI, newItem: RandomFoodRecipeUI
            ): Boolean {
                return newItem.id == oldItem.id
            }
        }
    }

    interface MenuCategoryInterface {
        fun menuCategoryToRecipe(id: Int)
    }
}