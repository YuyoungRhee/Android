package com.example.practice2

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practice2.databinding.ActivityMainBinding
import com.example.practice2.databinding.CustomDialogBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
   lateinit var binding : ActivityMainBinding
   lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)

        //토글 추가
        toggle = ActionBarDrawerToggle(this, binding.main, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //토글이랑 액션바랑 연결
        toggle.syncState() //토글이랑 액션바랑 연결

        val adapter = FragmentAdapter(this)
        binding.mainVp.adapter = adapter

        TabLayoutMediator(binding.mainTab, binding.mainVp){
            tab, position -> tab.text = "Tab ${position+1}"
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        //액션뷰인 SearchView에 대한 제어
        val searchView = menu?.findItem(R.id.main_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showSearchDialog(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    val eventHandler3 = object : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                Log.d("mobileapp", "BUTTON_POSITIVE")

            } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                Log.d("mobileapp", "BUTTON_NEGATIVE")
            }
        }
    }
    fun showSearchDialog(query:String?) {
        val dialogBindng = CustomDialogBinding.inflate(layoutInflater)
        AlertDialog.Builder(this).run() {
            setTitle("검색어 입력 확인")
            setIcon(android.R.drawable.ic_dialog_alert)
            dialogBindng.dialogSearchTv.text = query
            setView(dialogBindng.root)

            setNegativeButton("닫기", eventHandler3)
            show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        when(item.itemId) {
            R.id.main_login -> {
                Toast.makeText(this, "개발 중입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}