package com.example.mid20210786

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mid20210786.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra("title")
        binding.addTitle.text = title


    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = intent
        setResult(RESULT_OK, intent)
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu) //xml에서 불러옴

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {
                val title = binding.addTitle.text.toString()
                val name = binding.addNameEt.text.toString()
                val number = binding.addNumberEt.text.toString()
                Log.d("mobileapp", name)
                Log.d("mobileapp", number)
                val returnIntent = Intent()
                if(name != "" && number !=""){
                    returnIntent.putExtra("itemData", "$title\n$name\n$number")
                }




                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}