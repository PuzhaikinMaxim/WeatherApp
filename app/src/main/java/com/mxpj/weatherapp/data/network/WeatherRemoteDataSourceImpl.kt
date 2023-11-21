package com.mxpj.weatherapp.data.network

import com.mxpj.weatherapp.data.network.models.WeatherListDto
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val service: WeatherApiService
): WeatherRemoteDataSource {

    override suspend fun getWeatherDataForFiveDays(): WeatherListDto? {
        val response = service.getWeatherDataForFiveDays(55f, 37f)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }
}