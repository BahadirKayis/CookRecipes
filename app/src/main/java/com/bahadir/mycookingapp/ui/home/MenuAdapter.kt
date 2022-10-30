package com.bahadir.mycookingapp.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.data.model.Menu
import com.bahadir.mycookingapp.databinding.MenuCategoryBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.*


class MenuAdapter(private val food: List<Menu>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MenuCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Menu) {
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
                    .error(R.drawable.serving)
                    .into(foodImage)
                itemView.setOnClickListener {
                    itemView.findNavController().navigate(
                        RandomFoodFragmentDirections.actionRandomFoodFragmentToMenuFragment(
                            item.title.lowercase(
                                Locale.getDefault()
                            )
                        )
                    )
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


}