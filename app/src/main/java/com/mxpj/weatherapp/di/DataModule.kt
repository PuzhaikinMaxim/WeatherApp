package com.mxpj.weatherapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mxpj.weatherapp.data.*
import com.mxpj.weatherapp.data.local.WeatherAppDatabase
import com.mxpj.weatherapp.data.local.WeatherDao
import com.mxpj.weatherapp.data.network.ApiServiceFactory
import com.mxpj.weatherapp.data.network.WeatherApiService
import com.mxpj.weatherapp.data.network.WeatherRemoteDataSource
import com.mxpj.weatherapp.data.network.WeatherRemoteDataSourceImpl
import com.mxpj.weatherapp.domain.LocationRepository
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

    @Binds
    @Singleton
    fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository

    companion object {

        @Provides
        @Singleton
        fun provideWeatherApiService(): WeatherApiService {
            return ApiServiceFactory.create(WeatherApiService::class.java)
        }

        @Provides
        fun provideSharedPrefs(
            application: Application
        ): SharedPreferences {
            return application.getSharedPreferences(
                LocationRepositoryImpl.LOCATION_PREFERENCES_KEY,
                Context.MODE_PRIVATE
            )
        }

        @Provides
        fun provideWeatherDao(
            application: Application
        ): WeatherDao {
            return WeatherAppDatabase.getInstance(application).provideWeatherDao()
        }
    }
}