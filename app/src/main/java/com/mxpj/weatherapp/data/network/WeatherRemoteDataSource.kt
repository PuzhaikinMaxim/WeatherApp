package com.mxpj.weatherapp.data.network

import com.mxpj.weatherapp.data.network.models.WeatherListDto
import com.mxpj.weatherapp.domain.Location

interface WeatherRemoteDataSource {

    suspend fun getWeatherDataForFiveDays(location: Location): WeatherListDto?
}