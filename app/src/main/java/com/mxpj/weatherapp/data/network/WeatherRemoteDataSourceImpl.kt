package com.mxpj.weatherapp.data.network

import com.mxpj.weatherapp.data.network.models.WeatherListDto
import com.mxpj.weatherapp.domain.Location
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val service: WeatherApiService
): WeatherRemoteDataSource {

    override suspend fun getWeatherDataForFiveDays(location: Location): WeatherListDto? {
        try {
            val response = service.getWeatherDataForFiveDays(location.latitude, location.longitude)
            if(response.isSuccessful){
                return response.body()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return null
    }
}