package com.example.jetpackapplication

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.jetpackapplication.databinding.FragmentOneBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater, container, false)
        binding.fragButton.setOnClickListener {
            binding.oneFragment.setBackgroundColor(Color.parseColor("#00ffff"))

            Toast.makeText(context, "oneFragment", Toast.LENGTH_LONG).show()

            context?.let { it1 ->
                AlertDialog.Builder(it1).run {
                    setTitle("알림")
                    setIcon(android.R.drawable.ic_dialog_alert)
                    setPositiveButton("예", null)
                    setNegativeButton("아니오", null)
                    show()
                }
            }
        }
        return binding.root
    }

}