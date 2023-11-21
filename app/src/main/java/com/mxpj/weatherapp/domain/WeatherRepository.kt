package com.mxpj.weatherapp.domain

interface WeatherRepository {

    suspend fun getWeatherDataForFiveDays(location: Location): List<WeatherData>

    suspend fun getWeatherDetailedDataForDate(date: String): WeatherDetailedData
}