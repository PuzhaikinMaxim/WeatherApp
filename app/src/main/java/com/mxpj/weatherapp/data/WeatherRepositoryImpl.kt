package com.mxpj.weatherapp.data

import com.mxpj.weatherapp.data.network.WeatherRemoteDataSource
import com.mxpj.weatherapp.data.network.models.WeatherDto
import com.mxpj.weatherapp.domain.WeatherData
import com.mxpj.weatherapp.domain.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherDataMapper: WeatherDataMapper
): WeatherRepository {

    override suspend fun getWeatherDataForFiveDays(): List<WeatherData> {
        val weatherData = weatherRemoteDataSource.getWeatherDataForFiveDays()
        return weatherDataMapper.mapWeatherListDtoToWeatherDataList(weatherData)
    }
}