package com.example.busorsubway

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.busorsubway.databinding.ItemMainBinding

class JsonViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)
class JsonAdapter(val datas: MutableList<JsonItem>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return JsonViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as JsonViewHolder).binding
        val model = datas!![position]

//        binding.name.text = model.name
//        binding.manufacture.text = model.manufacture
//        binding.nutrient.text = model.nutrient
//
//        Glide.with(binding.root)
//            .load(model.imgurl1)
//            .override(400, 300)
//            .into(binding.urlImage)
//
//        Glide.with(binding.root)
//            .load(model.imgurl2)
//            .override(400, 300)
//            .into(binding.urlImage2)
    }
}
