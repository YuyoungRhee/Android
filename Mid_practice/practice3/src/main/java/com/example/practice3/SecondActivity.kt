package com.example.practice3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice3.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        binding.secondTitle.text = title

        binding.secondEndBtn.setOnClickListener {
            val name = "[$title]"+ binding.secondName.text.toString()
            val number = binding.secondNumber.text.toString()
            val returnIntent = Intent()
            returnIntent.putExtra("data", "$name\n$number")
            setResult(RESULT_OK,returnIntent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = intent
        setResult(RESULT_OK,intent)
        finish()
        return true
    }
}