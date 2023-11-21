package com.mxpj.weatherapp.data

import com.mxpj.weatherapp.data.network.models.WeatherDto
import com.mxpj.weatherapp.data.network.models.WeatherListDto
import com.mxpj.weatherapp.domain.WeatherData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WeatherDataMapper @Inject constructor() {

    fun mapWeatherListDtoToWeatherDataList(weatherListDto: WeatherListDto?): List<WeatherData> {
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
        val weatherDataList = mutableListOf<WeatherData>()
        dates.forEach {
            weatherDataList.add(mapWeatherDtoListToWeatherData(it.value, it.key))
        }
        return weatherDataList.reversed()
    }

    fun mapWeatherDtoListToWeatherData(list: List<WeatherDto>, date: LocalDate): WeatherData {
        var averageMinTemp = 0f
        var averageMaxTemp = 0f
        var averagePressure = 0
        var averageHumidity = 0
        var averageWindSpeed = 0f
        var averageCloudPercent = 0
        list.forEach {
            averageMinTemp += it.weatherMainDto.tempMin
            averageMaxTemp += it.weatherMainDto.tempMax
            averagePressure += it.weatherMainDto.pressure
            averageHumidity += it.weatherMainDto.humidity
            averageWindSpeed += it.windDto.speed
            averageCloudPercent += it.cloudPercentDto.percent
        }
        val midDayData = list[list.size/2]
        return WeatherData(
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
    }

    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")


        private fun getIconUrl(code: String): String {
            return "https://openweathermap.org/img/wn/${code}@2x.png"
        }
    }
}