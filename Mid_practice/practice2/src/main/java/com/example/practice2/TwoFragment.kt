package com.example.practice2

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
import com.example.practice2.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    lateinit var binding : FragmentTwoBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var datas = mutableListOf<String>()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // 결과 처리
                val data = result.data
                if (data != null) {
                    val name = data.getStringExtra("name")
                    val number = data.getStringExtra("number")
                    datas.add("$name\n$number")
                    adapter.notifyDataSetChanged()
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater, container, false)

        datas = savedInstanceState?.let{
            it.getStringArrayList("datas")?.toMutableList()
        } ?: let {
            mutableListOf<String>()
        }

        binding.twoFab.setOnClickListener{
            val intent = Intent(requireContext(), SecondActivity::class.java)
            if(binding.twoFriendRb.isChecked){
                intent.putExtra("title", binding.twoFriendRb.text)
            } else{
                intent.putExtra("title", binding.twoPlaceRb.text)
            }
            resultLauncher.launch(intent)
        }

        adapter = RecyclerAdapter(datas)
        binding.twoRecycler.adapter = adapter
        binding.twoRecycler.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // datas 상태 저장
        outState.putStringArrayList("datas", ArrayList(datas))
    }
}