package com.mxpj.weatherapp.presentation.fragments

import android.os.Bundle
import android.view.View
import com.mxpj.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.mxpj.weatherapp.presentation.BaseFragment

class WeatherDetailsFragment: BaseFragment<FragmentWeatherDetailsBinding>(
    FragmentWeatherDetailsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}