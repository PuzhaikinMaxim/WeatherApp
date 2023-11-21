package com.mxpj.weatherapp.data

import com.mxpj.weatherapp.data.local.WeatherDbModel
import com.mxpj.weatherapp.data.network.models.WeatherDto
import com.mxpj.weatherapp.data.network.models.WeatherListDto
import com.mxpj.weatherapp.domain.WeatherData
import com.mxpj.weatherapp.domain.WeatherDetailedData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WeatherDataMapper @Inject constructor() {

    fun mapWeatherListDtoToWeatherDetailedDataList(
        weatherListDto: WeatherListDto?
    ): List<WeatherDetailedData> {
        val weatherDtoList = weatherListDto?.list ?: listOf()
        val dates = HashMap<LocalDate, MutableList<WeatherDto>>()
        weatherDtoList.forEach {
            val date = LocalDate.parse(it.dateTxt.split(" ")[0])
            val list = dates[date] ?: mutableListOf()
            if(list.isEmpty()){
                dates[date] = list
            }
            list.add(it)
        }
        val weatherDataList = mutableListOf<WeatherDetailedData>()
        dates.forEach {
            weatherDataList.add(mapWeatherDtoListToWeatherDetailedData(it.value, it.key))
        }
        return weatherDataList.reversed()
    }

    fun mapWeatherDtoListToWeatherDetailedData(
        list: List<WeatherDto>,
        date: LocalDate
    ): WeatherDetailedData {
        var averageMinTemp = 0f
        var averageMaxTemp = 0f
        var averagePressure = 0
        var averageHumidity = 0
        var averageWindSpeed = 0f
        var averageCloudPercent = 0
        var averageDegrees = 0
        var averageFeelsLike = 0f
        list.forEach {
            averageMinTemp += it.weatherMainDto.tempMin
            averageMaxTemp += it.weatherMainDto.tempMax
            averagePressure += it.weatherMainDto.pressure
            averageHumidity += it.weatherMainDto.humidity
            averageWindSpeed += it.windDto.speed
            averageCloudPercent += it.cloudPercentDto.percent
            averageDegrees += it.windDto.degrees
            averageFeelsLike += it.weatherMainDto.feelsLike
        }
        val midDayData = list[list.size/2]
        val weatherData = WeatherData(
            midDayData.weatherDescriptionDto[0].description,
            date.format(dateFormatter),
            (averageMinTemp/list.size).toInt(),
            (averageMaxTemp/list.size).toInt(),
            averageCloudPercent/list.size,
            averageHumidity/list.size,
            averagePressure/list.size,
            (averageWindSpeed/list.size).toInt(),
            getIconUrl(midDayData.weatherDescriptionDto[0].icon)
        )
        return WeatherDetailedData(
            weatherData,
            averageDegrees/list.size,
            (averageFeelsLike/list.size).toInt()
        )
    }

    fun mapWeatherDetailedDataListToWeatherDbModelList(
        list: List<WeatherDetailedData>
    ): List<WeatherDbModel> {
        return list.mapIndexed { index, it ->
            mapWeatherDetailedDataToWeatherDbModel(it, index)
        }
    }

    fun mapWeatherDetailedDataToWeatherDbModel(
        weatherDetailedData: WeatherDetailedData,
        id: Int
    ): WeatherDbModel {
        return WeatherDbModel(
            id,
            weatherDetailedData.weatherData.date,
            weatherDetailedData.weatherData.description,
            weatherDetailedData.weatherData.minTemperature,
            weatherDetailedData.weatherData.maxTemperature,
            weatherDetailedData.weatherData.overcast,
            weatherDetailedData.weatherData.relativeHumidity,
            weatherDetailedData.weatherData.pressure,
            weatherDetailedData.weatherData.windSpeed,
            weatherDetailedData.weatherData.imageUrl,
            weatherDetailedData.angle,
            weatherDetailedData.feelsLike
        )
    }

    fun mapWeatherDbModelListToWeatherDataList(list: List<WeatherDbModel>): List<WeatherData> {
        return list.map { mapWeatherDbModelToWeatherData(it) }
    }

    fun mapWeatherDbModelToWeatherData(weatherDbModel: WeatherDbModel): WeatherData {
        return WeatherData(
            weatherDbModel.description,
            weatherDbModel.dateTxt,
            weatherDbModel.minTemperature,
            weatherDbModel.maxTemperature,
            weatherDbModel.overcast,
            weatherDbModel.relativeHumidity,
            weatherDbModel.pressure,
            weatherDbModel.windSpeed,
            weatherDbModel.imageUrl
        )
    }

    fun mapWeatherDbModelToWeatherDetailedData(weatherDbModel: WeatherDbModel): WeatherDetailedData {
        val weatherData = mapWeatherDbModelToWeatherData(weatherDbModel)
        return WeatherDetailedData(weatherData, weatherDbModel.angle, weatherDbModel.feelsLike)
    }

    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")


        private fun getIconUrl(code: String): String {
            return "https://openweathermap.org/img/wn/${code}@2x.png"
        }
    }
}