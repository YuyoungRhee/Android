package com.example.practice3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice3.databinding.FragmentTwoBinding
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TwoFragment : Fragment() {
    private lateinit var binding : FragmentTwoBinding
    private lateinit var adapter: TwoAdapter
    private var datas = mutableListOf<String>()
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

        adapter = TwoAdapter(datas)
        binding.twoRecycler.adapter = adapter
        binding.twoRecycler.layoutManager = LinearLayoutManager(requireContext())

        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            //데이터 처리
            it.data!!.getStringExtra("data")?.let {
                if(it != ""){
                    datas.add(it)
                    adapter.notifyDataSetChanged()
                }

            }
        }

        binding.twoFab.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            if(binding.twoFreindRbtn.isChecked){
                intent.putExtra("title", binding.twoFreindRbtn.text.toString())
            } else {
                intent.putExtra("title", binding.twoPlaceRbtn.text.toString())
            }

            requestLauncher.launch(intent)
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // datas 상태 저장
        outState.putStringArrayList("datas", ArrayList(datas))
    }

}