package com.mxpj.weatherapp.di

import android.app.Application
import com.mxpj.weatherapp.presentation.MainActivity
import com.mxpj.weatherapp.presentation.fragments.HomeFragment
import com.mxpj.weatherapp.presentation.fragments.WeatherDetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: HomeFragment)

    fun inject(fragment: WeatherDetailsFragment)

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}