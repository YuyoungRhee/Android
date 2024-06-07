package com.example.practice2

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
import com.example.practice2.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        binding.oneDateBtn.setOnClickListener {
            DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.oneDateBtn.text = "$year 년 ${month + 1} 월 $dayOfMonth 일"
                }
            }, 2024, 3, 3).show()
        }

        binding.oneTimeBtn.setOnClickListener {
            TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    if(hourOfDay < 12){
                        binding.oneTimeBtn.text = "오전 $hourOfDay 시 $minute 분"
                    }
                    else {
                        binding.oneTimeBtn.text = "오후 $hourOfDay 시 $minute 분"
                    }


                }
            }, 16, 56, true).show()
        }

        val items = arrayOf("Room 1", "Room 2", "Room 3")
        var selected = 0
        val eventHandler2 = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                    binding.oneRoomBtn.text = "${items[selected]}"
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                }
            }
        }
        binding.oneRoomBtn.setOnClickListener {
            AlertDialog.Builder(requireContext()).run() {
                setTitle("알림 - 색상 선택")
                setIcon(android.R.drawable.ic_dialog_alert)
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
            binding.oneResultTv.text =
                "예약자는 ${binding.oneNameEt.text}\n예약날짜는 ${binding.oneDateBtn.text} ${binding.oneTimeBtn.text}\n예약룸은 ${binding.oneRoomBtn.text}"
            binding.oneResultTv.visibility = View.VISIBLE
        }
        return binding.root
    }
}