package com.bahadir.mycookingapp.ui.filter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.data.model.remote.filter.MealTypes
import com.bahadir.mycookingapp.databinding.ItemFilterBinding


class MealTypeAdapter(private val list: List<MealTypes>) :
    RecyclerView.Adapter<MealTypeAdapter.ViewHolder>() {
    var listHigh: (List<MealTypes>) -> Unit = { list }

    inner class ViewHolder(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealTypes) {
            with(binding) {
                checkbox.isChecked = item.checked
                checkbox.text = item.name
                checkbox.setOnCheckedChangeListener { _, isChecked ->
                    item.checked = isChecked
                    listHigh(list)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}