package com.example.busorsubway

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.busorsubway.databinding.AddressItemBinding

class AddressViewHolder(val binding: AddressItemBinding) : RecyclerView.ViewHolder(binding.root)

class AddressAdapter(
    val context: Context,
    var itemList: MutableList<JsonItem>,
    private val onItemClick: (JsonItem) -> Unit
) : RecyclerView.Adapter<AddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AddressViewHolder(AddressItemBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val data = itemList[position]

        holder.binding.run {
            addressItemMainTv.text = data.bdNm
            addressItemDetailTv.text = data.roadAddr
            root.setOnClickListener { onItemClick(data) }
        }
    }
}
