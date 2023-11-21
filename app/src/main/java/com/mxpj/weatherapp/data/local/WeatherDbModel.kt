package com.mxpj.weatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherDbModel(
    @PrimaryKey
    val id: Int,
    val dateTxt: String,
    val description: String,
    val minTemperature: Int,
    val maxTemperature: Int,
    val overcast: Int,
    val relativeHumidity: Int,
    val pressure: Int,
    val windSpeed: Int,
    val imageUrl: String,
    val angle: Int,
    val feelsLike: Int
)