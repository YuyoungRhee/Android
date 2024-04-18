package com.example.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.practice.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment() {
    private lateinit var binding : FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        binding.albumLalacLayout1.setOnClickListener{
            Toast.makeText(activity, "LILAC", Toast.LENGTH_SHORT).show()

        }

        return binding.root
    }

}