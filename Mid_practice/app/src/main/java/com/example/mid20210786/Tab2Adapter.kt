package com.example.mid20210786

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mid20210786.databinding.ItemBinding

class Tab2Adapter(private val datalist: List<ItemData>): RecyclerView.Adapter<Tab2Adapter.Tab2ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tab2ViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return Tab2ViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: Tab2ViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

    class Tab2ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemData) {
            binding.itemNameTv.text = item.name
            binding.itemNumberTv.text = item.number
        }

    }
}