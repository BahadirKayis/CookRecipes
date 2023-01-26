package com.bahadir.mycookingapp.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.ClickToAny
import com.bahadir.mycookingapp.common.extensions.circularProgressDrawable
import com.bahadir.mycookingapp.common.extensions.color
import com.bahadir.mycookingapp.data.model.Menu
import com.bahadir.mycookingapp.databinding.ItemMenuCategoryBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MenuAdapter(private val food: List<Menu>, private val menuInterface: ClickToAny) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemMenuCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Menu) {
            with(binding) {
                itemView.animation =
                    AnimationUtils.loadAnimation(itemView.context, R.anim.menu_category_item_scale)
                Glide.with(foodImage.context).load(item.image).override(500, 500)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(foodImage.context.circularProgressDrawable())
                    .error(R.drawable.serving).into(foodImage)
                itemView.setOnClickListener {
                    menuInterface.onClickToAny(title = item.title)
                }
                cardView.setBackgroundColor(cardView.context.color(item.backgroundColor))
                title.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMenuCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(food[position])
    }

    override fun getItemCount(): Int {
        return food.size
    }
}
