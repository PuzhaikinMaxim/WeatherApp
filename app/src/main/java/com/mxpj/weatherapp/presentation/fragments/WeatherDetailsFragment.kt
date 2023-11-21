package com.mxpj.weatherapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mxpj.weatherapp.R
import com.mxpj.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.mxpj.weatherapp.presentation.BaseFragment
import com.mxpj.weatherapp.presentation.viewmodels.ViewModelFactory
import com.mxpj.weatherapp.presentation.viewmodels.WeatherDetailsViewModel
import javax.inject.Inject

class WeatherDetailsFragment: BaseFragment<FragmentWeatherDetailsBinding>(
    FragmentWeatherDetailsBinding::inflate
) {

    private lateinit var viewModel: WeatherDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val args by navArgs<WeatherDetailsFragmentArgs>()

    override fun onAttach(context: Context) {
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[WeatherDetailsViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setWeatherDetailedData(args.date)
        setupWeatherDetailedData()
    }

    private fun setupWeatherDetailedData() {
        viewModel.weatherDetailedData.observe(requireActivity()){
            binding.tvMinTemperature.text = getString(
                R.string.min_temperature_with_desc,
                it.weatherData.minTemperature
            )
            binding.tvMaxTemperature.text = getString(
                R.string.max_temperature_with_desc,
                it.weatherData.maxTemperature
            )
            binding.tvDate.text = it.weatherData.date
            binding.tvOvercast.text = getString(R.string.overcast_with_desc, it.weatherData.overcast)
            binding.tvFeelsLike.text = getString(R.string.feels_like, it.feelsLike)
            binding.tvAngle.text = getString(R.string.wind_angle, it.angle)
            binding.tvPressure.text = getString(R.string.pressure_with_desc, it.weatherData.pressure)
            binding.tvRelativeHumidity.text = getString(
                R.string.relative_humidity_with_desc,
                it.weatherData.relativeHumidity
            )
            binding.tvSpeed.text = getString(R.string.wind_speed_with_desc, it.weatherData.windSpeed)
        }
    }

    override fun onDetach() {
        if(!this::viewModel.isInitialized) return
        viewModel.weatherDetailedData.removeObservers(requireActivity())
        super.onDetach()
    }
}