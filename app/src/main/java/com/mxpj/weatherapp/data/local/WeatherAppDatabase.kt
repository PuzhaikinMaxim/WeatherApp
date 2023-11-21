package com.mxpj.weatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [WeatherDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherAppDatabase: RoomDatabase() {

    abstract fun provideWeatherDao(): WeatherDao

    companion object {

        private var db: WeatherAppDatabase? = null
        private const val DB_NAME = "weather_app.db"
        private val LOCK = Any()

        fun getInstance(context: Context): WeatherAppDatabase {
            synchronized(LOCK){
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    WeatherAppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }
}