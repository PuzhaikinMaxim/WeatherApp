package com.mxpj.weatherapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxpj.weatherapp.domain.GetWeatherDataForFiveDaysUseCase
import com.mxpj.weatherapp.domain.WeatherData
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getWeatherDataForFiveDaysUseCase: GetWeatherDataForFiveDaysUseCase
): ViewModel() {

    private val _weatherDataList = MutableLiveData<List<WeatherData>>()
    val weatherDataList: LiveData<List<WeatherData>>
        get() = _weatherDataList

    init {
        viewModelScope.launch {
            val weatherDataList = getWeatherDataForFiveDaysUseCase()
            _weatherDataList.value = weatherDataList
        }
    }
}