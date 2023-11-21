package com.mxpj.weatherapp.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mxpj.weatherapp.R
import com.mxpj.weatherapp.databinding.ActivityMainBinding
import com.mxpj.weatherapp.presentation.viewmodels.MainViewModel
import com.mxpj.weatherapp.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val component by lazy { (application as WeatherApplication).component }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        setLocation()
        viewModel.isLocationPermissionGranted.observe(this){
            if(it){
                setContentView(binding.root)
                setupBottomNavigationMenu()
            }
        }
    }

    private fun setLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                FINE_LOCATION
            ) != PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                COARSE_LOCATION
            ) != PERMISSION_GRANTED
        ) {
            setPermissionLauncher(FINE_LOCATION)
            setPermissionLauncher(COARSE_LOCATION)
        } else {
            val gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            val latitude = gpsLocation?.latitude?.toFloat()
                ?: networkLocation?.latitude?.toFloat() ?: 0f
            val longitude = gpsLocation?.longitude?.toFloat()
                ?: networkLocation?.longitude?.toFloat() ?: 0f
            viewModel.setLocationPermissionGranted()
            viewModel.setLocation(latitude, longitude)
        }
    }

    private fun setPermissionLauncher(permission: String) {
        if (ActivityCompat.checkSelfPermission(this, permission) == PERMISSION_GRANTED) {
            return
        }
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if(!it){
                setLocation()
            }
        }.launch(permission)
    }

    private fun setupBottomNavigationMenu() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_main) as NavHostFragment
        binding.bnMain.setupWithNavController(navHostFragment.navController)
    }

    companion object {

        private const val PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED

        private const val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION

        private const val COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    }
}