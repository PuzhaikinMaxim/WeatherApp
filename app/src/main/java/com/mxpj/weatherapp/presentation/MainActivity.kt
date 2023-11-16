package com.mxpj.weatherapp.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mxpj.weatherapp.R
import com.mxpj.weatherapp.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNavigationMenu()
    }

    private fun setupBottomNavigationMenu() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_main) as NavHostFragment
        binding.bnMain.setupWithNavController(navHostFragment.navController)
    }
}