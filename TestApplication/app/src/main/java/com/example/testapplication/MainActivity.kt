package com.example.testapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Log.i
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = Handler(Looper.getMainLooper())

        val imageList = arrayListOf<Int>()

        imageList.add(R.drawable.fruits)
        imageList.add(R.drawable.vegetable)
        imageList.add(R.drawable.meat)
        imageList.add(R.drawable.fruits)
        imageList.add(R.drawable.vegetable)
        imageList.add(R.drawable.meat)
        imageList.add(R.drawable.fruits)
        imageList.add(R.drawable.vegetable)
        imageList.add(R.drawable.meat)

        Thread {
            for (image in imageList) {
                handler.post{
                    Log.d("mobileapp", "이미지 변경")
                    binding.iv.setImageResource(image)

                }
                Thread.sleep(2000)

            }
        }.start()


    }


}
