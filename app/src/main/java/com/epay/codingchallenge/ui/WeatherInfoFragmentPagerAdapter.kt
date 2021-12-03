package com.epay.codingchallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.epay.codingchallenge.ui.weatherinfo.WeatherInfoFragment

/**
 * Created By Rafiqul Hasan
 */
class WeatherInfoFragmentPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                WeatherInfoFragment().apply {
                    arguments = getLatLonArguments(-22.90278, -43.2075)
                }
            }
            1 -> {
                WeatherInfoFragment().apply {
                    arguments = getLatLonArguments(39.9075, 116.39723)
                }
            }
            else -> {
                WeatherInfoFragment().apply {
                    arguments = getLatLonArguments(34.05223, -118.24368)
                }
            }
        }
    }

    private fun getLatLonArguments(latitude: Double, longitude: Double): Bundle {
        return Bundle().apply {
            putDouble(WeatherInfoFragment.ARG_LATITUDE, latitude)
            putDouble(WeatherInfoFragment.ARG_LONGITUDE, longitude)
        }
    }

}