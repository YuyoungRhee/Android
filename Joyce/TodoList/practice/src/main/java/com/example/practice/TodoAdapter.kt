package com.example.practice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.practice.databinding.ItemTodoBinding

class TodoAdapter(private val todos : List<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context)//얘를 parent에 연결해주는건데 우리가 직접 해주는게아니고 알아서 해야되기떄문데 false로함
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        //정보를 바인딩함
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.todoTitleTv.text = todo.title
            binding.todoCheckboxCb.isChecked = todo.completed
        }
    }


}