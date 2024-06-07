package com.example.jetpackapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackapplication.databinding.FragmentTwoBinding
import com.example.jetpackapplication.databinding.ItemRecyclerviewBinding

class MyViewHolder(val binding:ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)
class MyRecyclerAdapter(val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemData.text = datas[position]
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        //그림 먼저 -> 항목
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), 0f, 0f, null)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //항목 먼저 -> 그림
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), 500f, 500f, null)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        //view.setBackgroundColor(Color.parseColor("#345678"))
    }
}
class TwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTwoBinding.inflate(inflater, container, false)

        var datas = mutableListOf<String>()
        for(i in 1 .. 10) {
            datas.add("item $i")
        }

        //adapter & viewholder
        val adapter = MyRecyclerAdapter(datas)
        binding.recyclerView.adapter = adapter

        //layoutManager
        var linear  = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = linear
        linear.orientation = LinearLayoutManager.HORIZONTAL

        var grid = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = grid

        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))

        binding.mainFab.setOnClickListener {
            datas.add("Android Add")
            adapter.notifyDataSetChanged() //어댑터에게 데이터가 바뀌었다고 알려줌

        }
        return binding.root
    }

}