package com.example.mid20210786

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
import com.example.mid20210786.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    private lateinit var binding : FragmentOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
                    binding.oneDateTv.text = "$year 년 ${month + 1} 월 $dayOfMonth 일"

                }
            }, 2024, 3, 27).show()
        }

        binding.oneTimeBtn.setOnClickListener {
            TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Toast.makeText(requireContext(), "$hourOfDay 시 $minute 분", Toast.LENGTH_LONG)
                        .show()
                    binding.oneTimeTv.text = "$hourOfDay 시 $minute 분"

                }
            }, 14, 0, true).show()
        }
        val item1 = arrayOf("많이", "보통", "적게")
        var selected = 1
        val eventHandler2 = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    binding.oneMealTv.text = "${item1[selected]}"
                    Toast.makeText(requireContext(), "${item1[selected]} 선택.", Toast.LENGTH_SHORT).show()
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    //
                }
            }
        }
        binding.oneMealBtn.setOnClickListener {
            AlertDialog.Builder(requireContext()).run() {
                setTitle("식사량 선택하기")
                setIcon(android.R.drawable.ic_dialog_alert)

                setSingleChoiceItems(item1, selected, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        selected = which
                    }
                })
                setPositiveButton("예", eventHandler2)
                setNegativeButton("아니오", eventHandler2)
                show()
            }
        }



        var multiSelected =booleanArrayOf(false, false)
        val item2 = arrayOf("나트륨 적게", "설탕 적게")
        val eventHandler = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                var multiSelectedText = ""
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    if (multiSelected[0] == true){
                        multiSelectedText += "나트륨 적게 "
                    }
                    if (multiSelected[1] == true){
                        multiSelectedText += "설탕 적게"
                    }

                    binding.oneEtcTv.text = multiSelectedText
                    Toast.makeText(requireContext(), "$multiSelectedText", Toast.LENGTH_SHORT).show()
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                }
            }
        }
        binding.oneEtcBtn.setOnClickListener {
            multiSelected =booleanArrayOf(false, false)
            AlertDialog.Builder(requireContext()).run() {
                setTitle("특이사항 선택")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMultiChoiceItems(item2, booleanArrayOf(false, false), object : DialogInterface.OnMultiChoiceClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                        Log.d("mobileapp", "$which ${if(isChecked) "선택" else "해제"}")
                        if(isChecked) {
                            multiSelected[which] = true
                        } else {
                            multiSelected[which] = false
                        }
                    }
                })
                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                show()
            }
        }

        binding.oneEndBtn.setOnClickListener {
            binding.oneEndBtn.text = "수정"
            binding.oneResultTv.visibility = View.VISIBLE
            binding.oneResultTv.text = "${binding.oneDateTv.text}\n ${binding.oneTimeTv.text}\n${binding.oneMealTv.text}\n${binding.oneEtcTv.text}"
        }




        return binding.root
    }






}