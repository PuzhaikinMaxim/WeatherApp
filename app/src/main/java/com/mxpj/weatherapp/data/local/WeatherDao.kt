package com.mxpj.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    suspend fun getWeatherDataForFiveDays(): List<WeatherDbModel>

    @Query("DELETE FROM weather")
    suspend fun clearWeatherTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeatherData(list: List<WeatherDbModel>)

    @Query("SELECT * FROM weather w WHERE w.dateTxt =:date")
    suspend fun getWeatherForDay(date: String): WeatherDbModel
}