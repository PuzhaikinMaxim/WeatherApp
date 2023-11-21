package com.mxpj.weatherapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mxpj.weatherapp.domain.LocationRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val locationRepository: LocationRepository
): ViewModel() {

    private val _isLocationPermissionGranted = MutableLiveData<Boolean>()
    val isLocationPermissionGranted: LiveData<Boolean>
        get() = _isLocationPermissionGranted

    fun setLocation(latitude: Float, longitude: Float) {
        locationRepository.setLocation(latitude, longitude)
    }

    fun setLocationPermissionGranted() {
        _isLocationPermissionGranted.value = true
    }
}