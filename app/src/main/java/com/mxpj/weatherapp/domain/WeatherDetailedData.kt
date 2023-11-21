package com.mxpj.weatherapp.domain

data class WeatherDetailedData(
    val weatherData: WeatherData,
    val angle: Int,
    val feelsLike: Int
)