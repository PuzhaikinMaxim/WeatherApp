package com.mxpj.weatherapp.data.network

import com.mxpj.weatherapp.data.network.models.WeatherListDto

interface WeatherRemoteDataSource {

    suspend fun getWeatherDataForFiveDays(): WeatherListDto?
}