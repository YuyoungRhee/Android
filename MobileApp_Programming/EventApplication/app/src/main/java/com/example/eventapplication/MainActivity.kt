package com.example.eventapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.example.eventapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.text = "시작"
        binding.startButton.textSize = 24.0f

        var prevTime = 0L
        binding.startButton.setOnClickListener{
            binding.chronometer.base = SystemClock.elapsedRealtime() + prevTime
            binding.chronometer.start()
            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true

        }
        binding.stopButton.setOnClickListener{
            prevTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true

        }
        binding.resetButton.setOnClickListener{
            prevTime = 0L
            binding.chronometer.stop()
            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = false

        }
    } //OnCreate

    private var initTime = 0L
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_BACK -> {
                //처음 눌렸을 때
                if(System.currentTimeMillis() - initTime > 3000) { //3
                    Log.d("mobileapp", "BACK key가 눌렸어요.. 종료하려면 한 번 더 누르세요")
                    initTime = System.currentTimeMillis() //처음 버튼이 클릭된 시간

                    Toast.makeText(this, "BACK key가 눌렸어요.. 종료하려면 한 번 더 누르세요", Toast.LENGTH_LONG).show()
                    return true
                }
                //두 번째 눌렸을 초 초과
            }
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("mobileapp", "VOLUME_UP key가 눌렸어요..")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("mobileapp", "VOLUME_DOWN key가 눌렸어요..")
        }
        return super.onKeyDown(keyCode, event)
    }
}