package com.epay.codingchallenge.ui.weatherinfo

import android.os.Bundle
import android.util.Log
import android.view.View
import com.epay.codingchallenge.R
import com.epay.codingchallenge.core.fragment.BaseFragment
import com.epay.codingchallenge.databinding.FragmentWeatherInfoBinding

/**
 * Created By Rafiqul Hasan
 */
class WeatherInfoFragment:BaseFragment<FragmentWeatherInfoBinding>() {
    companion object{
        const val ARG_LATITUDE = "ARG_LATITUDE"
        const val ARG_LONGITUDE = "ARG_LONGITUDE"
    }
    override val layoutResourceId: Int
        get() = R.layout.fragment_weather_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("error","onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("error","onViewCreated")
    }
}