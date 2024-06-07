package com.example.mid20210786

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mid20210786.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        val title = intent.getStringExtra("title")
        binding.secondTitleTv.text = "$title 추가하기"

        setContentView(binding.root)

        binding.endBtn.setOnClickListener {
            val returnIntent = Intent()

            returnIntent.putExtra("name", "[$title]" + binding.secondNameEt.text.toString())
            returnIntent.putExtra("number", binding.secondNumberEt.text.toString())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

    }
}