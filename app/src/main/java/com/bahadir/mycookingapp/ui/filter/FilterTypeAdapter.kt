package com.bahadir.mycookingapp.ui.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.data.model.remote.filter.FilterTypes
import com.bahadir.mycookingapp.databinding.ItemFilterBinding

class FilterTypeAdapter(private val list: List<FilterTypes>) :
    RecyclerView.Adapter<FilterTypeAdapter.ViewHolder>() {
    var listHigh: (List<FilterTypes>) -> Unit = { list }

    inner class ViewHolder(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FilterTypes) {
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