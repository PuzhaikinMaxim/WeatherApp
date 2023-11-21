package com.mxpj.weatherapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxpj.weatherapp.domain.WeatherData
import com.mxpj.weatherapp.domain.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val _weatherDataList = MutableLiveData<List<WeatherData>>()
    val weatherDataList: LiveData<List<WeatherData>>
        get() = _weatherDataList

    init {
        viewModelScope.launch {
            val weatherDataList = weatherRepository.getWeatherDataForFiveDays()
            _weatherDataList.value = weatherDataList
        }
    }
}