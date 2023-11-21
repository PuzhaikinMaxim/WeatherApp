package com.mxpj.weatherapp.presentation.fragments.optionfragments

import android.os.Bundle
import android.view.View
import com.mxpj.weatherapp.R

class Option3Fragment: BaseOptionFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvStub.text = getString(R.string.option_stub, 3)
    }
}