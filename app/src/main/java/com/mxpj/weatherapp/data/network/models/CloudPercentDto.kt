package com.mxpj.weatherapp.data.network.models

import com.google.gson.annotations.SerializedName

data class CloudPercentDto(
    @SerializedName("all")
    val percent: Int
)