package com.example.practice3

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.practice3.databinding.FragmentOneBinding

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
    lateinit var binding : FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        binding.oneDateBtn.setOnClickListener {
            DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Toast.makeText(
                        requireContext(),
                        "$year 년 ${month + 1} 월 $dayOfMonth 일",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.oneDateBtn.text = "$year 년 ${month + 1} 월 $dayOfMonth 일"
                }
            }, 2024, 3, 3).show()
        }

        binding.oneTimeBtn.setOnClickListener {
            TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Toast.makeText(requireContext(), "$hourOfDay 시 $minute 분", Toast.LENGTH_LONG)
                        .show()
                    binding.oneTimeBtn.text = "$hourOfDay 시 $minute 분"

                }
            }, 16, 56, true).show()
        }

        val items = arrayOf("Room 1", "Room 2", "Room 3")
        var selected = 0
        val eventHandler2 = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    binding.oneRoomBtn.text = "${items[selected]}"
                    Toast.makeText(requireContext(), "${items[selected]}이 최종 선택되었습니다.", Toast.LENGTH_SHORT).show()
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Toast.makeText(requireContext(), "예약룸 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.oneRoomBtn.setOnClickListener {
            AlertDialog.Builder(requireContext()).run() {
                setTitle("예약 선택하기")

                setSingleChoiceItems(items, selected, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        selected = which
                    }
                })
                setPositiveButton("OK", eventHandler2)
                setNegativeButton("CANCEL", eventHandler2)
                show()
            }
        }

        binding.oneEndBtn.setOnClickListener {
            binding.oneResultTv.visibility = View.VISIBLE
            binding.oneResultTv.text = "예약자는 ${binding.oneNameEt.text}\n" +
                    "예약 날짜는 ${binding.oneDateBtn.text} ${binding.oneTimeBtn.text}\n" +
                    "예약룸은 ${binding.oneRoomBtn.text}"
        }
        return binding.root
    }



}