package com.example.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practice.databinding.FragmentSongBinding
import com.example.practice.databinding.FragmentVideoBinding

class SongFragment : Fragment() {
    lateinit var binding : FragmentSongBinding

    var ismixBtnOn = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)

        binding.albumSongMixIv.setOnClickListener {
            if(ismixBtnOn){
                binding.albumSongMixIv.setImageResource(R.drawable.btn_toggle_off)
                ismixBtnOn = false
            }
            else {
                binding.albumSongMixIv.setImageResource(R.drawable.btn_toggle_on)
                ismixBtnOn = true
            }
        }

        return binding.root
    }
}