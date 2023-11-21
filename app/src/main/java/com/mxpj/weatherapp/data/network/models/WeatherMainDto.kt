package com.mxpj.weatherapp.data.network.models

import com.google.gson.annotations.SerializedName

data class WeatherMainDto(
    @SerializedName("temp_min")
    val tempMin: Float,
    @SerializedName("temp_max")
    val tempMax: Float,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("feels_like")
    val feelsLike: Float
)