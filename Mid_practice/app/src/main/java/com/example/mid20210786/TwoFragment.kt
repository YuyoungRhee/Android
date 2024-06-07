package com.example.mid20210786

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mid20210786.databinding.FragmentOneBinding
import com.example.mid20210786.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    lateinit var binding : FragmentTwoBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val datas = ArrayList<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // 결과 처리
                val data = result.data
                if (data != null) {
                    val name = data.getStringExtra("name")
                    val number = data.getStringExtra("number")
                    datas.add(ItemData(name, number))
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater, container, false)

        recyclerViews()

        binding.twoFab.setOnClickListener {
            var intent = Intent(requireContext(), SecondActivity::class.java)

            if(binding.twoFreindBtn.isChecked){
                intent.putExtra("title", binding.twoFreindBtn.text)
            } else{
                intent.putExtra("title", binding.twoPlaceBtn.text)
            }

            resultLauncher.launch(intent)
        }

        return binding.root
    }

    private fun recyclerViews() {
        binding.twoRecycler.layoutManager = LinearLayoutManager(context)
        binding.twoRecycler.adapter = Tab2Adapter(datas)
    }

}