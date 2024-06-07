package com.example.mid20210786

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.mid20210786.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    private lateinit var binding : ActivityMainBinding
    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var adapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //네비게이션 리스너 추가
        binding.mainDrawer.setNavigationItemSelectedListener(this)

        adapter = FragmentAdapter(this)
        binding.mainVp.adapter = adapter
        binding.mainVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.mainTab, binding.mainVp) { tab, position ->
            tab.text = "${position + 2}번"
        }.attach()

        //토글 추가
        toggle = ActionBarDrawerToggle(this, binding.main, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //토글이랑 액션바랑 연결
        toggle.syncState() //토글이랑 액션바랑 연결

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.item_map -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.65198,127.0162"))
                startActivity(intent)

                true
            }
            R.id.item_mapfind -> {
                //길찾기
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/덕성여자대학교/수유역"))
                startActivity(intent)
                true
            }
            R.id.item_call -> {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:911"))
                startActivity(intent)
                true
            }
            R.id.item_mail -> {
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:kmlee@ds.ac.kr"))
                startActivity(intent)
                true
            }
        }
        binding.main.closeDrawers()
        return false
    }
}