package com.example.mid20210786

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
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
import com.example.mid20210786.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        var date = ""
        binding.dateBtn.setOnClickListener {
            DatePickerDialog(requireActivity(), object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Toast.makeText(
                        requireActivity(),
                        "$year 년 ${month + 1} 월 $dayOfMonth 일",
                        Toast.LENGTH_LONG
                    ).show()
                    date = "$year 년 ${month + 1} 월 $dayOfMonth 일"
                    binding.dateBtn.text = "$year 년 ${month + 1} 월 $dayOfMonth 일"
                }
            }, 2024, 3, 3).show()
        }

        var time = ""
        binding.timeBtn.setOnClickListener {
            TimePickerDialog(requireActivity(), object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    time = "오전 $hourOfDay 시 $minute 분"
                    if (hourOfDay < 12) {
                        binding.timeBtn.text = "오전 $hourOfDay 시 $minute 분"
                    } else
                        binding.timeBtn.text = "오후 $hourOfDay 시 $minute 분"
                }
            }, 16, 56, true).show()
        }

        var selected = 0

        val items = arrayOf<String>("Room 1", "Room 2", "Room 3")
        var room = ""
        val eventHandler2 = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                    Toast.makeText(requireActivity(),"${items[selected]}이 선택되었습니다.", Toast.LENGTH_SHORT).show()
                    binding.roomBtn.text ="${items[selected]}"
                    room = "${items[selected]}"
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                    Toast.makeText(requireActivity(),"예약룸 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.roomBtn.setOnClickListener {
            AlertDialog.Builder(requireActivity()).run() {
                setTitle("예약룸 선택하기")
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

        binding.finishBtn.setOnClickListener {
            val username = binding.nameEt.text.toString()

            binding.finishTv.text = "예약자는 " + username +"\n예약 날짜는 " + date + time + "\n예약룸은 " + room
            binding.finishTv.visibility = View.VISIBLE
        }
        return binding.root
    }
}