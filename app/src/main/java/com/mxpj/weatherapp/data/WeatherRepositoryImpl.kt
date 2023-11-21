package com.mxpj.weatherapp.data

import com.mxpj.weatherapp.data.local.WeatherDao
import com.mxpj.weatherapp.data.network.WeatherRemoteDataSource
import com.mxpj.weatherapp.domain.Location
import com.mxpj.weatherapp.domain.WeatherData
import com.mxpj.weatherapp.domain.WeatherDetailedData
import com.mxpj.weatherapp.domain.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherDataMapper: WeatherDataMapper,
    private val weatherLocalDataSource: WeatherDao
): WeatherRepository {

    override suspend fun getWeatherDataForFiveDays(location: Location): List<WeatherData> {
        val weatherData = weatherRemoteDataSource.getWeatherDataForFiveDays(location)
        val detailedWeatherDataList =
            weatherDataMapper.mapWeatherListDtoToWeatherDetailedDataList(weatherData)
        return if(detailedWeatherDataList.isEmpty()){
            getWeatherDataFromLocalDataSource()
        }else{
            saveWeatherData(detailedWeatherDataList)
            detailedWeatherDataList.map { it.weatherData }
        }
    }

    override suspend fun getWeatherDetailedDataForDate(date: String): WeatherDetailedData {
        return weatherDataMapper.mapWeatherDbModelToWeatherDetailedData(
            weatherLocalDataSource.getWeatherForDay(date)
        )
    }

    private suspend fun saveWeatherData(weatherDataList: List<WeatherDetailedData>) {
        weatherLocalDataSource.clearWeatherTable()
        val weatherDbModelData = weatherDataMapper.mapWeatherDetailedDataListToWeatherDbModelList(
            weatherDataList
        )
        weatherLocalDataSource.addWeatherData(weatherDbModelData)
    }

    private suspend fun getWeatherDataFromLocalDataSource(): List<WeatherData> {
        return weatherDataMapper.mapWeatherDbModelListToWeatherDataList(
            weatherLocalDataSource.getWeatherDataForFiveDays()
        )
    }
}