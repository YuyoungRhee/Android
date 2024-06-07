package com.example.practice2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding : ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        binding.secondTitle.text = "[$title] 추가하기"

        binding.secondEndBtn.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("name", "[$title]" + binding.secondNameEt.text.toString())
            returnIntent.putExtra("number", binding.secondNumberEt.text.toString())
            setResult(RESULT_OK,returnIntent)
            finish()
        }







    }
}