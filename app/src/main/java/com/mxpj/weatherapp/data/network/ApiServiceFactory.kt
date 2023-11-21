package com.mxpj.weatherapp.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceFactory {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(RequestQueryParametersInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    fun<T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}