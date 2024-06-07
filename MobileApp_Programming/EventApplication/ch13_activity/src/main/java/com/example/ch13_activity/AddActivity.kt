package com.example.ch13_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch13_activity.databinding.ActivityAddBinding
import com.example.ch13_activity.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val date = intent.getStringExtra("today")
        binding.date.text = date

        binding.btnSave.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("todo", binding.addEditView.text.toString())
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