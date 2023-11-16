package com.mxpj.weatherapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mxpj.weatherapp.databinding.FragmentHomeBinding
import com.mxpj.weatherapp.presentation.BaseFragment
import com.mxpj.weatherapp.presentation.adapters.WeatherListAdapter
import com.mxpj.weatherapp.presentation.viewmodels.HomeViewModel
import com.mxpj.weatherapp.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWeatherList()
    }

    private fun setupWeatherList() {
        val adapter = WeatherListAdapter(requireActivity())
        viewModel.weatherDataList.observe(requireActivity()){
            adapter.weatherDataList = it
        }
        binding.rvRecentWeather.adapter = adapter
    }
}