package com.mxpj.weatherapp.presentation

import android.app.Application
import com.mxpj.weatherapp.di.DaggerApplicationComponent

class WeatherApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}