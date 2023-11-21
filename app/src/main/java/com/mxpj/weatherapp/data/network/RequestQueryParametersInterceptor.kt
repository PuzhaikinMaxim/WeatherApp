package com.mxpj.weatherapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestQueryParametersInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrlWithQueryParams = request.url()
            .newBuilder()
            .addQueryParameter(LANG_KEY, LANG)
            .addQueryParameter(APPID_KEY, APPID)
            .addQueryParameter(UNITS_KEY, UNITS)
            .build()

        val requestWithQueryParams = request.newBuilder()
            .url(httpUrlWithQueryParams)
            .build()
        return chain.proceed(requestWithQueryParams)
    }

    companion object {
        const val LANG = "ru"

        const val LANG_KEY = "lang"

        const val UNITS = "metric"

        const val UNITS_KEY = "units"

        const val APPID = "ca7849e05ae3949cd0db24af858d1f8a"

        const val APPID_KEY = "appid"
    }
}