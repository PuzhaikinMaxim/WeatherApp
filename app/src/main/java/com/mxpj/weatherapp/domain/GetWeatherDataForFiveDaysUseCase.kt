package com.mxpj.weatherapp.domain

import javax.inject.Inject

class GetWeatherDataForFiveDaysUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(): List<WeatherData> {
        val location = locationRepository.getLocation()
        return weatherRepository.getWeatherDataForFiveDays(location)
    }
}