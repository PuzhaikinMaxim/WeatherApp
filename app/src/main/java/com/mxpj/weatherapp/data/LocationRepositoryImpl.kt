package com.mxpj.weatherapp.data

import android.content.SharedPreferences
import com.mxpj.weatherapp.domain.Location
import com.mxpj.weatherapp.domain.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): LocationRepository {

    override fun setLocation(latitude: Float, longitude: Float) {
        val editor = sharedPreferences.edit()
        editor.putFloat(LATITUDE_KEY, latitude)
        editor.putFloat(LONGITUDE_KEY, longitude)
        editor.apply()
    }

    override fun getLocation(): Location {
        val latitude = sharedPreferences.getFloat(LATITUDE_KEY, 0f)
        val longitude = sharedPreferences.getFloat(LONGITUDE_KEY, 0f)
        return Location(latitude, longitude)
    }

    companion object {
        private const val LATITUDE_KEY = "latitude"

        private const val LONGITUDE_KEY = "longitude"

        const val LOCATION_PREFERENCES_KEY = "location"
    }
}