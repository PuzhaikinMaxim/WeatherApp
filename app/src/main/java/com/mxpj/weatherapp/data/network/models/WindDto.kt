package com.mxpj.weatherapp.data.network.models

import com.google.gson.annotations.SerializedName

data class WindDto(
    @SerializedName("speed")
    val speed: Float,
    @SerializedName("deg")
    val degrees: Int
)