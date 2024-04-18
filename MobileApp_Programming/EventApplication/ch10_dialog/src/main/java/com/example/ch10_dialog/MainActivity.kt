package com.example.ch10_dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import androidx.appcompat.widget.SearchView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.ch10_dialog.databinding.ActivityMainBinding
import com.example.ch10_dialog.databinding.DialogCustomBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding : ActivityMainBinding
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainDrawerView.setNavigationItemSelectedListener(this)


        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.btnDate.setOnClickListener {
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Toast.makeText(
                        applicationContext,
                        "$year 년 ${month + 1} 월 $dayOfMonth 일",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.btnDate.text = "$year 년 ${month + 1} 월 $dayOfMonth 일"
                    binding.btnDate.textSize = 24f
                    binding.btnDate.setTextColor(Color.parseColor("#ffff00"))
                }
            }, 2024, 3, 3).show()
        }

        binding.btnTime.setOnClickListener {
            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Toast.makeText(applicationContext, "$hourOfDay 시 $minute 분", Toast.LENGTH_LONG)
                        .show()
                    binding.btnTime.text = "$hourOfDay 시 $minute 분"
                    binding.btnTime.textSize = 24f
                    binding.btnTime.setTextColor(Color.parseColor("#00ffff"))

                }
            }, 16, 56, true).show()
        }


        //여러번 사용되기때문에 리스너를 따로 뺌
        val eventHandler = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                }
            }
        }
        binding.btnAlert.setOnClickListener {
            AlertDialog.Builder(this).run() {
                setTitle("알림 - 모바일 앱")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMessage("정말로 종료하시겠습니까?")
                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                setNeutralButton("상세 설명", eventHandler)
                show()
            }
        }

        val items = arrayOf<String>("빨강", "파랑", "노랑", "초록")
        binding.btnAlertItem.setOnClickListener {
            AlertDialog.Builder(this).run() {
                setTitle("알림 - 색상 선택")
                setIcon(android.R.drawable.ic_dialog_alert)
                setItems(items, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("mobileapp", "${items[which]} 선택")
                        binding.btnAlertItem.text = "${items[which]} 선택"
                        binding.btnAlertItem.textSize = 24f
                        binding.btnAlertItem.setTextColor(Color.parseColor("#ff00ff"))
                    }
                })
                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                show()
            }
        }

        var selected = 0
        val eventHandler2 = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                    binding.btnAlertSingle.text = "${items[selected]} 선택"
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                }
            }
        }
        binding.btnAlertSingle.setOnClickListener {
            AlertDialog.Builder(this).run() {
                setTitle("알림 - 색상 선택")
                setIcon(android.R.drawable.ic_dialog_alert)
                setSingleChoiceItems(items,2, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        selected = which
                    }
                })
                setPositiveButton("예", eventHandler2)
                setNegativeButton("아니오", eventHandler2)
                show()
            }
            binding.btnAlertMulti.setOnClickListener {
                AlertDialog.Builder(this).run() {
                    setTitle("알림 - 다수 선택")
                    setIcon(android.R.drawable.ic_dialog_alert)
                    setMultiChoiceItems(items, booleanArrayOf(false, true, false, true), object : DialogInterface.OnMultiChoiceClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                            Log.d("mobileapp", "$which ${if(isChecked) "선택" else "해제"}")
                        }
                    })
                    setPositiveButton("예", eventHandler)
                    setNegativeButton("아니오", eventHandler)
                    show()
                }

            }

            val dialogBindng = DialogCustomBinding.inflate(layoutInflater)
            val eventHandler3 = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        Log.d("mobileapp", "BUTTON_POSITIVE")
                        if(dialogBindng.rbtn1.isChecked) {
                                binding.btnAlertCustom.text = dialogBindng.rbtn1.text.toString()
                            }
                        else if (dialogBindng.rbtn2.isChecked) {
                            binding.btnAlertCustom.text = dialogBindng.rbtn2.text.toString()
                        }
                    } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                        Log.d("mobileapp", "BUTTON_NEGATIVE")
                    }
                }
            }
            binding.btnAlertCustom.setOnClickListener {
                AlertDialog.Builder(this).run() {
                    setTitle("알림 - 사용자 화면")
                    setIcon(android.R.drawable.ic_dialog_alert)

                    setView(dialogBindng.root)

                    setPositiveButton("예", eventHandler3)
                    setNegativeButton("아니오", eventHandler3)
                    show()
                }

            }
        }
    } //onCreate()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        when(item.itemId){
            R.id.item1 -> {
                Log.d("mobileapp", "Navigation menu: 메뉴 1")
                binding.btnDate.setTextColor(Color.parseColor("#ffff00"))
                true
            }
            R.id.item2 -> {
                Log.d("mobileapp", "Navigation menu: 메뉴 2")
                true
            }
            R.id.item3 -> {
                Log.d("mobileapp", "Navigation menu: 메뉴 3")
                true
            }
            R.id.item4 -> {
                Log.d("mobileapp", "Navigation menu: 메뉴 4")
                true
            }
        }
        return false
    }
    //Option Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_navigation, menu)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, "$query 검색합니다.", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        when(item.itemId){
            R.id.item1 -> {
                Log.d("mobileapp", "Option menu: 메뉴 1")
                binding.btnDate.setTextColor(Color.parseColor("#ffff00"))
                true
            }
            R.id.item2 -> {
                Log.d("mobileapp", "Option menu: 메뉴 2")
                true
            }
            R.id.item3 -> {
                Log.d("mobileapp", "Option menu: 메뉴 3")
                true
            }
            R.id.item4 -> {
                Log.d("mobileapp", "Option menu: 메뉴 4")
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}