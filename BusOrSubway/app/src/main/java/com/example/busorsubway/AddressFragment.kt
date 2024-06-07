package com.example.busorsubway

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.busorsubway.databinding.FragmentAddressBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder

class AddressFragment : Fragment() {
    private lateinit var binding: FragmentAddressBinding
    private lateinit var adapter: AddressAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater, container, false)

        recyclerView = binding.addressItemRecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = AddressAdapter(requireContext(), mutableListOf()) { selectedAddress ->
            // 도로명주소를 사용하여 좌표 변환 API 호출
            getCoordinates(selectedAddress.roadAddr) { x, y ->
                val type = arguments?.getString("type")
                parentFragmentManager.setFragmentResult(
                    "addressRequestKey",
                    Bundle().apply {
                        putString("selectedAddress", selectedAddress.bdNm)
                        putString("type", type)
                        putString("latitude", y)
                        putString("longitude", x)
                    }
                )
                parentFragmentManager.popBackStack()
            }
        }
        recyclerView.adapter = adapter

        initSearchView()
        return binding.root
    }

    private fun initSearchView() {
        binding.addressSearch.isSubmitButtonEnabled = true
        binding.addressSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchAddress(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun searchAddress(keyword: String) {
        val serviceKey = "devU01TX0FVVEgyMDI0MDYwNTA5Mzg1NjExNDgyMzU= "
        val currentPage = 1
        val countPerPage = 10

        val call = RetrofitConnection.jsonNetworkService.getJsonList(
            keyword = keyword,
            currentPage = currentPage,
            countPerPage = countPerPage,
            confmKey = serviceKey
        )

        call.enqueue(object : Callback<JsonResponse> {
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
                if (response.isSuccessful) {
                    val jsonResponse = response.body()
                    jsonResponse?.let {
                        val expandedList = mutableListOf<JsonItem>()
                        for (juso in it.results.juso) {
                            expandedList.add(juso)
                        }
                        adapter.itemList.clear()
                        adapter.itemList.addAll(expandedList)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("AddressFragment", "Response failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                Log.e("AddressFragment", "Network request failed", t)
            }
        })
    }

    private fun getCoordinates(address: String, callback: (String, String) -> Unit) {

        val call = RetrofitConnection.coordinateNetworkService.getCoordinates(
            service = "address",
            request = "getcoord",
            version = "2.0",
            crs = "epsg:4326",
            address = address,
            refine = true,
            simple = false,
            format = "json",
            type = "road",
            key = "6E2F7DE8-D0F9-35D6-AA05-A7BF500B27F0"
        )

        call.enqueue(object : Callback<CoordinateResponse> {
            override fun onResponse(call: Call<CoordinateResponse>, response: Response<CoordinateResponse>) {
                if (response.isSuccessful) {
                    val coordinateResponse = response.body()
                    Log.d("AddressFragment", "Coordinate Response: ${response.raw()}")
                    Log.d("AddressFragment", "Coordinate Response Body: ${response.body()}")
                    val point = coordinateResponse?.response?.result?.point
                    if (point != null) {
                        val x = point.x
                        val y = point.y
                        callback(x, y)
                    } else {
                        Log.e("AddressFragment", "Coordinate response or point is null: ${response.body()}")
                    }
                } else {
                    Log.e("AddressFragment", "Coordinate response failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<CoordinateResponse>, t: Throwable) {
                Log.e("AddressFragment", "Coordinate request failed", t)
            }
        })
    }
}
