package com.mxpj.weatherapp.di

import androidx.lifecycle.ViewModel
import com.mxpj.weatherapp.presentation.viewmodels.MainViewModel
import com.mxpj.weatherapp.presentation.viewmodels.HomeViewModel
import com.mxpj.weatherapp.presentation.viewmodels.WeatherDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherDetailsViewModel::class)
    fun bindWeatherDetailsViewModel(weatherDetailsViewModel: WeatherDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}