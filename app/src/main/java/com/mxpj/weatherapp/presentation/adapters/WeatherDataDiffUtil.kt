package com.mxpj.weatherapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.mxpj.weatherapp.domain.WeatherData

class WeatherDataDiffUtil(
    private val oldList: List<WeatherData>,
    private val newList: List<WeatherData>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].date == newList[newItemPosition].date
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}