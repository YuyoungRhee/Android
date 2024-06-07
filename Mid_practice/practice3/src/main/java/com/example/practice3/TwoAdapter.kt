package com.example.practice3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice3.databinding.TwoItemBinding

class TwoAdapter(val datas : MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(TwoItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.twoItemName.text = datas[position].split("\n")[0]
        binding.twoItemNumber.text = datas[position].split("\n")[1]

    }

    class MyViewHolder(val binding: TwoItemBinding) : RecyclerView.ViewHolder(binding.root)

}