package com.mxpj.weatherapp.domain

interface LocationRepository {

    fun setLocation(latitude: Float, longitude: Float)

    fun getLocation(): Location
}