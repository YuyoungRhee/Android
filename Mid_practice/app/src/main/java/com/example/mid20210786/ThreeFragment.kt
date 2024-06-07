package com.example.mid20210786

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mid20210786.databinding.FragmentOneBinding
import com.example.mid20210786.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {
    lateinit var binding : FragmentThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

}