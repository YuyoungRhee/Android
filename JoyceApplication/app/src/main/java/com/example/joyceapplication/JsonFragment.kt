package com.example.joyceapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.joyceapplication.databinding.FragmentJsonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonFragment : Fragment() {
    lateinit var  binding:FragmentJsonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentJsonBinding.inflate(inflater, container, false)

        val year = arguments?.getString("searchYear") ?: "2024"
        //http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo
        val call : Call<JsonResponse> = RetrofitConnection.jsonNetServ.getJsonList(
            year.toInt(),
            1,
            10,
            "json",
            "Evy3WRyOic0TBycRLNRo5HcE2ulCFmPk5OowkpfGhL7FN9uQ80zbnhLLAVG5XrcZa8UErm5yINg/4LD8fzkQPg=="
        )

        call?.enqueue(object: Callback<JsonResponse>{
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
                if(response.isSuccessful){
                    Log.d("mobileapp", "$response")
                    Log.d("mobileapp", "${response.body()}")
                    binding.jsonRecyclerView.adapter = JsonAdapter(response.body()?.response!!.body!!.items)
                    binding.jsonRecyclerView.layoutManager = LinearLayoutManager(activity)
                    binding.jsonRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
                }
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                Log.d("mobileapp", "onfailure")
            }

        })

        return binding.root
    }

}