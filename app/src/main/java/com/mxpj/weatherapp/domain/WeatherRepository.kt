package com.mxpj.weatherapp.domain

interface WeatherRepository {

    suspend fun getWeatherDataForFiveDays(): List<WeatherData>
}