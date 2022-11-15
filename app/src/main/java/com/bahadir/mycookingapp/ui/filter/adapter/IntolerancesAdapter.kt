package com.bahadir.mycookingapp.ui.filter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahadir.mycookingapp.data.model.filter.Intolerances
import com.bahadir.mycookingapp.databinding.ItemFilterBinding


class IntolerancesAdapter(private val list: List<Intolerances>) :
    RecyclerView.Adapter<IntolerancesAdapter.ViewHolder>() {
    var listHigh: (List<Intolerances>) -> Unit = { list }

    inner class ViewHolder(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Intolerances) {
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