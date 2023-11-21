package com.mxpj.weatherapp.data.network.models

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("dt")
    val date: Int,
    @SerializedName("dt_txt")
    val dateTxt: String,
    @SerializedName("main")
    val weatherMainDto: WeatherMainDto,
    @SerializedName("weather")
    val weatherDescriptionDto: List<WeatherDescriptionDto>,
    @SerializedName("wind")
    val windDto: WindDto,
    @SerializedName("clouds")
    val cloudPercentDto: CloudPercentDto
) {
}