package com.example.joyceapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joyceapplication.databinding.FragmentXmlBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class XmlFragment : Fragment() {
    lateinit var binding : FragmentXmlBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXmlBinding.inflate(inflater, container, false)

        val year = arguments?.getString("searchYear")?:"2024"

        val call: Call<XmlResponse> = RetrofitConnection.xmlNetServ.getXmlList(
            year.toInt(),
            1,
            10,
            "xml",
            "Evy3WRyOic0TBycRLNRo5HcE2ulCFmPk5OowkpfGhL7FN9uQ80zbnhLLAVG5XrcZa8UErm5yINg/4LD8fzkQPg=="
        )

        call?.enqueue(object:Callback<XmlResponse>{
            override fun onResponse(call: Call<XmlResponse>, response: Response<XmlResponse>) {
                Log.d("mobileapp", "$response")
                Log.d("mobileapp", "${response.body()}")
                binding.xmlRecyclerView.adapter = XmlAdapter(response.body()!!.body!!.items!!.item)
                binding.xmlRecyclerView.layoutManager = LinearLayoutManager(activity)
                binding.xmlRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            }

            override fun onFailure(call: Call<XmlResponse>, t: Throwable) {
                Log.d("mobileapp", "onFailure ${call.request()}")
            }

        })

        return binding.root
    }
}