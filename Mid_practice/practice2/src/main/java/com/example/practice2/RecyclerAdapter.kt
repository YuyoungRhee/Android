package com.example.practice2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice2.databinding.RecyclerItemBinding

class RecyclerAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        binding.recyclerNameTv.text = datas[position].split("\n")[0]
        binding.recyclerNumberTv.text = datas[position].split("\n")[1]
    }

    override fun getItemCount(): Int = datas.size


    class MyViewHolder( val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}