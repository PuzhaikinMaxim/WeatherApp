package com.mxpj.weatherapp.data.network

import com.mxpj.weatherapp.data.network.models.WeatherListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("forecast")
    suspend fun getWeatherDataForFiveDays(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float
    ): Response<WeatherListDto>
}