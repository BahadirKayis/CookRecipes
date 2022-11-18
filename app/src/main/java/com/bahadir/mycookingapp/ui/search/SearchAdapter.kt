package com.bahadir.mycookingapp.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.R
import com.bahadir.mycookingapp.common.glideImage
import com.bahadir.mycookingapp.data.model.remote.search.SearchResult
import com.bahadir.mycookingapp.databinding.ItemSearchRecipeBinding


class SearchAdapter(
    private val searchRecipe: List<SearchResult>,
    private val searchAdapterInterface: SearchAdapterInterface
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSearchRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchResult) {
            try {
                with(item) {
                    with(binding) {
                        itemView.animation = AnimationUtils.loadAnimation(
                            itemView.context, R.anim.menu_category_item_scale
                        )

                        foodImage.glideImage(item.image)
                        title.text = item.title


                        itemView.setOnClickListener {
                            searchAdapterInterface.searchToRecipe(id)
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
            ItemSearchRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(searchRecipe[position])

    override fun getItemCount(): Int {
        return searchRecipe.size
    }

    interface SearchAdapterInterface {
        fun searchToRecipe(id: Int)
    }
}




