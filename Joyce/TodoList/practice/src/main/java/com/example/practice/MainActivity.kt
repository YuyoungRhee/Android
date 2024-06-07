package com.example.practice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val todos = listOf(
        Todo("todo example 1", false),
        Todo("todo example 2", false),
        Todo("todo example 3", false),
        Todo("todo example 4", false),
        Todo("todo example 5", false),
        Todo("todo example 6", false),
        Todo("todo example 7", false),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        binding.todoListRv.layoutManager = LinearLayoutManager(this)
        binding.todoListRv.adapter = TodoAdapter(todos)
    }
}