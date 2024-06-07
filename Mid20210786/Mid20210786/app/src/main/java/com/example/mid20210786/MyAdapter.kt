package com.example.mid20210786

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mid20210786.databinding.ItemRecyclerViewBinding

class MyAdapter(val datas : MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
       if(datas[position].split("\n")[0].contains("과일"))
       {
           binding.recyclerIv.setImageResource(R.drawable.fruits)
       }
       else if(datas[position].split("\n")[0].contains("채소"))
       {
           binding.recyclerIv.setImageResource(R.drawable.vegetable)
       }
       else if(datas[position].split("\n")[0].contains("육류"))
       {
           binding.recyclerIv.setImageResource(R.drawable.meat)
       }

        binding.name.text = datas[position].split("\n")[1]
        binding.number.text = datas[position].split("\n")[2]

    }

    class MyViewHolder(val binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

}