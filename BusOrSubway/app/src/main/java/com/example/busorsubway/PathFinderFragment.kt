package com.example.busorsubway

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import com.example.busorsubway.databinding.FragmentPathFinderBinding

class PathFinderFragment : Fragment() {
    private lateinit var binding: FragmentPathFinderBinding
    private val viewModel: PathFinderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPathFinderBinding.inflate(inflater, container, false)

        binding.departureTv.setOnClickListener {
            navigateToAddressFragment("departure")
        }
        binding.arrivalTv.setOnClickListener {
            navigateToAddressFragment("arrival")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.departure.observe(viewLifecycleOwner) { departure ->
            binding.departureTv.text = departure
        }

        viewModel.arrival.observe(viewLifecycleOwner) { arrival ->
            binding.arrivalTv.text = arrival
        }

        setFragmentResultListener("addressRequestKey") { _, bundle ->
            val result = bundle.getString("selectedAddress")
            val latitude = bundle.getString("latitude")
            val longitude = bundle.getString("longitude")
            val type = bundle.getString("type")
            when (type) {
                "departure" -> {
                    viewModel.setDeparture(result ?: "")
                    viewModel.setDepartureCoordinates(latitude ?: "", longitude ?: "")
                }
                "arrival" -> {
                    viewModel.setArrival(result ?: "")
                    viewModel.setArrivalCoordinates(latitude ?: "", longitude ?: "")
                }
            }
        }
    }

    private fun navigateToAddressFragment(type: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentFrame, AddressFragment().apply {
                arguments = Bundle().apply {
                    putString("type", type)
                }
            })
            .addToBackStack(null)
            .commit()
    }
}
