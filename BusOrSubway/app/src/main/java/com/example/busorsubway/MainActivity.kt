package com.example.busorsubway

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.busorsubway.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    // DrawerLayout Toggle
    lateinit var toggle: ActionBarDrawerToggle

    lateinit var headerView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val pathFinderFragment = PathFinderFragment()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentFrame, PathFinderFragment())
                .commit()
        }

        MyApplication.auth = FirebaseAuth.getInstance()

        // DrawerLayout Toggle
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        // Drawer 메뉴
        binding.mainDrawerView.setNavigationItemSelectedListener(this)

        headerView = binding.mainDrawerView.getHeaderView(0)
        val button = headerView.findViewById<Button>(R.id.btnAuth)
        button.setOnClickListener {
            Log.d("mobileapp", "button,setOnClickListener")
            val intent = Intent(this, AuthActivity::class.java)
            if (button.text.equals("로그인")) {
                intent.putExtra("status", "logout")
            } else if (button.text.equals("로그아웃")) {
                intent.putExtra("status", "login")
            }
            startActivity(intent)
            binding.drawer.closeDrawers()
        }
    } //onCreate

    // DrawerLayout Toggle
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    // Drawer 메뉴
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_board -> {
                Log.d("moblieapp", "게시판 메뉴")
                val intent = Intent(this, LogActivity::class.java)
                startActivity(intent)
            }
            R.id.item_setting -> {
                Log.d("mobileapp", "설정 메뉴")
                binding.drawer.closeDrawers()
                true
            }

        }
        return false
    }

    override fun onStart() {
        super.onStart()

        val button = headerView.findViewById<Button>(R.id.btnAuth)
        val tv = headerView.findViewById<TextView>(R.id.tvID)

        if (MyApplication.checkAuth()) {
            button.text = "로그아웃"
            tv.text = "${MyApplication.email}님 \n 반갑습니다."
        } else {
            button.text = "로그인"
            tv.text = "안녕하세요"
        }

    }


}