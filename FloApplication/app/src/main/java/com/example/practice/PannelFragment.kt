package com.example.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practice.databinding.FragmentBannerBinding
import com.example.practice.databinding.FragmentHomeBinding
import com.example.practice.databinding.FragmentPannelBinding


class PannelFragment(val imgRes: Int) : Fragment() {
    lateinit var binding : FragmentPannelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPannelBinding.inflate(inflater, container, false)

        binding.pannelImageIv.setImageResource(imgRes)
        return binding.root
    }


}