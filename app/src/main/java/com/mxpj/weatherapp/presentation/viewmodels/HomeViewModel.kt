package com.mxpj.weatherapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mxpj.weatherapp.domain.WeatherData
import javax.inject.Inject

class HomeViewModel @Inject constructor(): ViewModel() {

    private val _weatherDataList = MutableLiveData<List<WeatherData>>()
    val weatherDataList: LiveData<List<WeatherData>>
        get() = _weatherDataList

    init {
        val weatherList = listOf(WeatherData(
            "16.11.2023",
            20,
            20,
            30,
            30,
            30
        ))
        _weatherDataList.value = weatherList
    }
}