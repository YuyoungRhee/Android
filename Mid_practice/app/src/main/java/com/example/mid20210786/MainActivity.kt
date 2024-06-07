package com.example.mid20210786

import android.app.AlertDialog
import android.app.Notification.Action
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.mid20210786.databinding.ActivityMainBinding
import com.example.mid20210786.databinding.DialogBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainNav.setNavigationItemSelectedListener(this)

        //토글, 액션바 설정 부분
        val toolbar = binding.mainToolbar
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(
            this,
            binding.main,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //토글이랑 액션바랑 연결
        toggle.syncState() //토글이랑 액션바랑 연결

        val vpAdapter = VPAdapter(this)
        binding.mainVp.adapter = vpAdapter
        binding.mainVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.mainTab, binding.mainVp) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            R.id.item_login -> {
                Toast.makeText(applicationContext, "개발 중입니다.", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        //액션뷰인 SearchView에 대한 제어
        val searchView = menu?.findItem(R.id.item_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //커스텀 다이얼로그
                showDialog(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }


    val eventHandler = object : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                Log.d("mobileapp", "BUTTON_POSITIVE")
            } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                Log.d("mobileapp", "BUTTON_NEGATIVE")
            }
        }
    }
    fun showDialog(query: String?){
        val dialogBindng = DialogBinding.inflate(layoutInflater)

        AlertDialog.Builder(this).run() {
            setTitle("검색어 입력 확인")
            setIcon(android.R.drawable.ic_dialog_alert)
            dialogBindng.dialogSearch.text = query
            setView(dialogBindng.root)
            setNegativeButton("닫기", eventHandler)
            show()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId) {
            R.id.item_school -> {
                val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://duksung.ac.kr"))
                startActivity(intent)
            }
            R.id.item_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:/119")
                startActivity(intent)
            }

        }
        return true
    }

}