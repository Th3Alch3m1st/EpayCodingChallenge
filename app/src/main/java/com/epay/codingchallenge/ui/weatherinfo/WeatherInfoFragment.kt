package com.epay.codingchallenge.ui.weatherinfo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.epay.codingchallenge.R
import com.epay.codingchallenge.core.fragment.BaseFragment
import com.epay.codingchallenge.databinding.FragmentWeatherInfoBinding
import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.ui.WeatherInfoViewModel
import com.epay.codingchallenge.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * Created By Rafiqul Hasan
 */
@AndroidEntryPoint
class WeatherInfoFragment : BaseFragment<FragmentWeatherInfoBinding>() {
    companion object {
        const val ARG_LATITUDE = "ARG_LATITUDE"
        const val ARG_LONGITUDE = "ARG_LONGITUDE"
    }

    val viewModel: WeatherInfoViewModel by viewModels()
    var adapterHourlyWeatherInfo: HourlyWeatherInfoAdapter by autoCleared()

    override val layoutResourceId: Int
        get() = R.layout.fragment_weather_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val latitude = arguments?.getDouble(ARG_LATITUDE)
        val longitude = arguments?.getDouble(ARG_LONGITUDE)
        viewModel.getWeatherInfo(latitude!!, longitude!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterHourlyWeatherInfo = HourlyWeatherInfoAdapter()
        dataBinding.rvHourlyWeatherInfo.adapter = adapterHourlyWeatherInfo
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.weatherInfoStateFlow.collect { response ->
                when (response) {
                    is NetworkResult.Loading -> {
                        fragmentCommunicator?.showLoader()
                    }
                    is NetworkResult.Success -> {
                        fragmentCommunicator?.hideLoader()
                        Log.e("error", response.data.toString())
                        adapterHourlyWeatherInfo.setHourlyWeatherInfo(response.data.hourly)
                    }

                    is NetworkResult.Error -> {
                        fragmentCommunicator?.hideLoader()
                        Log.e("error", response.exception.localizedMessage)
                    }
                }
            }
        }
    }
}