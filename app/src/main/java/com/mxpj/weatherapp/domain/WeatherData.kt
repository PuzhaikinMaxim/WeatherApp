package com.mxpj.weatherapp.domain

data class WeatherData(
    val date: String,
    val minTemperature: Int,
    val maxTemperature: Int,
    val overcast: Int,
    val relativeHumidity: Int,
    val pressure: Int
)