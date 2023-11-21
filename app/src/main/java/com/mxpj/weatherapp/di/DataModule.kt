package com.mxpj.weatherapp.di

import com.mxpj.weatherapp.data.*
import com.mxpj.weatherapp.data.network.ApiServiceFactory
import com.mxpj.weatherapp.data.network.WeatherApiService
import com.mxpj.weatherapp.data.network.WeatherRemoteDataSource
import com.mxpj.weatherapp.data.network.WeatherRemoteDataSourceImpl
import com.mxpj.weatherapp.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun bindWeatherRemoteDataSource(impl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    @Binds
    @Singleton
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    companion object {

        @Provides
        @Singleton
        fun provideWeatherApiService(): WeatherApiService {
            return ApiServiceFactory.create(WeatherApiService::class.java)
        }
    }
}