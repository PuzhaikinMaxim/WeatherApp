package com.mxpj.weatherapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mxpj.weatherapp.R
import com.mxpj.weatherapp.databinding.ItemWeatherDataBinding
import com.mxpj.weatherapp.domain.WeatherData
import com.squareup.picasso.Picasso

class WeatherListAdapter(
    private val context: Context
): Adapter<WeatherListAdapter.WeatherItemViewHolder>() {

    var weatherDataList = listOf<WeatherData>()
        set(value) {
            val callback = WeatherDataDiffUtil(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        val binding = ItemWeatherDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        val item = weatherDataList[position]
        with(holder.binding){
            tvDate.text = item.date
            tvTemperature.text = context.getString(
                R.string.temperature_range,
                item.minTemperature,
                item.maxTemperature
            )
            tvOvercast.text = context.getString(R.string.overcast, item.overcast)
            tvRelativeHumidity.text = context.getString(R.string.relative_humidity, item.relativeHumidity)
            tvPressure.text = context.getString(R.string.pressure, item.pressure)
            tvDescription.text = item.description
            tvWindSpeed.text = context.getString(R.string.wind_speed, item.windSpeed)
            Picasso.get().load(item.imageUrl).into(ivWeatherIcon)
        }
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    class WeatherItemViewHolder(val binding: ItemWeatherDataBinding): ViewHolder(binding.root)
}