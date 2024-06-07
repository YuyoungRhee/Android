package com.example.mid20210786

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mid20210786.databinding.DialogTypeBinding
import com.example.mid20210786.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    private lateinit var binding : FragmentTwoBinding
    private var datas = mutableListOf<String>()
    private lateinit var adapter: MyAdapter
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

        adapter = MyAdapter(datas)
        binding.twoRecycler.adapter = adapter
        binding.twoRecycler.layoutManager = LinearLayoutManager(requireContext())


        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            //데이터 처리
            it.data!!.getStringExtra("itemData")?.let {
                if(it != ""){
                    Log.d("mobileapp", it)
                    datas.add(it)

                    adapter.notifyDataSetChanged()
                }
            }
        }

        val dialogBindng = DialogTypeBinding.inflate(layoutInflater)
        val eventHandler3 = object : DialogInterface.OnClickListener {
            val intent = Intent(requireContext(), AddActivity::class.java)

            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                    if(dialogBindng.rbtn1.isChecked) {
                       intent.putExtra("title", dialogBindng.rbtn1.text.toString())

                    }
                    else if (dialogBindng.rbtn2.isChecked) {
                        intent.putExtra("title", dialogBindng.rbtn2.text.toString())
                    }
                    else if (dialogBindng.rbtn3.isChecked) {
                        intent.putExtra("title", dialogBindng.rbtn3.text.toString())
                    }
                    requestLauncher.launch(intent)

                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                }

            }


        }
        val customDialog = AlertDialog.Builder(requireContext()).run() {
            setTitle("종류 선택")


            setView(dialogBindng.root)

            setPositiveButton("예", eventHandler3)
            setNegativeButton("아니오", eventHandler3)
            create()
        }

        binding.twoFab.setOnClickListener {
            dialogBindng.rbtn1.isChecked = true
            customDialog.show()
        }


        return binding.root
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // datas 상태 저장
        outState.putStringArrayList("datas", ArrayList(datas))
    }

}