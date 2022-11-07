package com.bahadir.mycookingapp.ui.favorite


import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.glideImage
import com.bahadir.mycookingapp.databinding.ItemFavoriteRecipeBinding
import com.bahadir.mycookingapp.domain.model.RecipeUI


class FavoriteAdapter(private val favoriteInterface: FavoriteAdapterInterface) :
    ListAdapter<RecipeUI, FavoriteAdapter.ViewHolder>(FavoriteItemCallBack()) {


    inner class ViewHolder(private val binding: ItemFavoriteRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipeUI) {
            with(binding) {
                itemView.animation =
                    AnimationUtils.loadAnimation(
                        itemView.context,
                        R.anim.favorite_item
                    )
                foodImage.glideImage(item.imageFilePath)
                recipeText.text = item.instructions
                itemView.setOnClickListener {
                    favoriteInterface.recipeId(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFavoriteRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class FavoriteItemCallBack : DiffUtil.ItemCallback<RecipeUI>() {
        override fun areItemsTheSame(
            oldItem: RecipeUI, newItem: RecipeUI
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RecipeUI, newItem: RecipeUI
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface FavoriteAdapterInterface {
        fun recipeId(recipeId: Int)
    }

}