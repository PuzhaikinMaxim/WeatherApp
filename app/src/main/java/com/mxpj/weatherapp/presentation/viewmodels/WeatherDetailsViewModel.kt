package com.mxpj.weatherapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxpj.weatherapp.domain.WeatherDetailedData
import com.mxpj.weatherapp.domain.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherDetailsViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val _weatherDetailedData = MutableLiveData<WeatherDetailedData>()
    val weatherDetailedData: LiveData<WeatherDetailedData>
        get() = _weatherDetailedData

    fun setWeatherDetailedData(date: String) {
        viewModelScope.launch {
            val weatherDetailedData = weatherRepository.getWeatherDetailedDataForDate(date)
            _weatherDetailedData.value = weatherDetailedData
        }
    }
}