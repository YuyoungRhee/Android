package com.example.practice3

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
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
import androidx.viewpager2.widget.ViewPager2
import com.example.practice3.databinding.ActivityMainBinding
import com.example.practice3.databinding.DialogCustomBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FragmentAdapter
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //네비게이션 리스너 추가
        binding.mainDrawer?.setNavigationItemSelectedListener(this)

        adapter = FragmentAdapter(this)
        binding.mainVp.adapter = adapter
        binding.mainVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.mainTab, binding.mainVp) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()

        setSupportActionBar(binding.mainToolbar)

        //토글 추가
        toggle = ActionBarDrawerToggle(
            this,
            binding.main,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //토글이랑 액션바랑 연결
        toggle.syncState() //토글이랑 액션바랑 연결
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu) //xml에서 불러옴

        //액션뷰인 SearchView에 대한 제어
        val searchView = menu?.findItem(R.id.main_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                customDialog(query)

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
    fun customDialog(query:String?){
        val dialogBinding = DialogCustomBinding.inflate(layoutInflater)

        AlertDialog.Builder(this).run() {
            setTitle("검색어 입력 확인")
            dialogBinding.customSearch.text = query

            setView(dialogBinding.root)

            setNegativeButton("닫기", eventHandler3)
            show()
        }
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            R.id.main_login -> {
                Toast.makeText(this, "개발 중입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_info -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.duksung.ac.kr"))
                startActivity(intent)
                true
            }
            R.id.item_map -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.6513783, 127.0163402"))
                //길찾기
                //val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/덕성여자대학교/안국역"))

                startActivity(intent)
                true
            }
            R.id.item_call -> {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:119"))
                startActivity(intent)
                true
            }

        }
        return false
    }
}