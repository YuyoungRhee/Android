package com.example.busorsubway

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PathFinderViewModel : ViewModel() {
    private val _departure = MutableLiveData<String>()
    val departure: LiveData<String> get() = _departure

    private val _arrival = MutableLiveData<String>()
    val arrival: LiveData<String> get() = _arrival

    private val _departureCoordinates = MutableLiveData<Pair<String, String>>()
    val departureCoordinates: LiveData<Pair<String, String>> get() = _departureCoordinates

    private val _arrivalCoordinates = MutableLiveData<Pair<String, String>>()
    val arrivalCoordinates: LiveData<Pair<String, String>> get() = _arrivalCoordinates

    fun setDeparture(departure: String) {
        _departure.value = departure
    }

    fun setArrival(arrival: String) {
        _arrival.value = arrival
    }

    fun setDepartureCoordinates(latitude: String, longitude: String) {
        _departureCoordinates.value = Pair(latitude, longitude)
    }

    fun setArrivalCoordinates(latitude: String, longitude: String) {
        _arrivalCoordinates.value = Pair(latitude, longitude)
    }
}
