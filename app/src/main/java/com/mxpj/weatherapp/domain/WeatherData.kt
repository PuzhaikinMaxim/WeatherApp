package com.mxpj.weatherapp.domain

data class WeatherData(
    val description: String,
    val date: String,
    val minTemperature: Int,
    val maxTemperature: Int,
    val overcast: Int,
    val relativeHumidity: Int,
    val pressure: Int,
    val windSpeed: Int,
    val imageUrl: String
)