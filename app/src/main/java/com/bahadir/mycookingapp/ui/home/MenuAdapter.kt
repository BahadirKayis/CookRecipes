package com.bahadir.mycookingapp.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.circularProgressDrawable
import com.bahadir.mycookingapp.data.model.Menu
import com.bahadir.mycookingapp.databinding.MenuCategoryBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class MenuAdapter(private val food: List<Menu>, private val menuInterface: MenuAdapterInterface) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MenuCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Menu) {
            with(binding) {
                itemView.animation =
                    AnimationUtils.loadAnimation(itemView.context, R.anim.menu_category_item_scale)

                Glide.with(foodImage.context)
                    .load(item.image)
                    .override(500, 500) //1
                    .diskCacheStrategy(DiskCacheStrategy.DATA) //6
                    .placeholder(foodImage.context.circularProgressDrawable())
                    .error(R.drawable.serving)
                    .into(foodImage)

                itemView.setOnClickListener {
                    menuInterface.menuToCategories(item.title)
                }
                cardView.setBackgroundColor(
                    ContextCompat.getColor(
                        cardView.context,
                        (item.backgroundColor)
                    )
                )
                title.text = item.title
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MenuCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(food[position])
    }

    override fun getItemCount(): Int {
        return food.size
    }

    interface MenuAdapterInterface {
        fun menuToCategories(category: String)
    }
}
