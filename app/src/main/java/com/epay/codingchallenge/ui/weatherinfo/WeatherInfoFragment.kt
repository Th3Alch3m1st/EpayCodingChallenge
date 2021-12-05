package com.epay.codingchallenge.ui.weatherinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.epay.codingchallenge.R
import com.epay.codingchallenge.core.fragment.BaseFragment
import com.epay.codingchallenge.databinding.FragmentWeatherInfoBinding
import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.ui.WeatherInfoViewModel
import com.epay.codingchallenge.utils.autoCleared
import com.epay.codingchallenge.utils.gone
import com.epay.codingchallenge.utils.show
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

    private val viewModel: WeatherInfoViewModel by viewModels()
    private var adapterHourlyWeatherInfo: HourlyWeatherInfoAdapter by autoCleared()
    private var adapterDailyWeatherInfo: DailyWeatherInfoAdapter by autoCleared()

    override val layoutResourceId: Int
        get() = R.layout.fragment_weather_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWeatherInfo()
        initAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserver()
    }

    private fun initAdapter() {
        adapterHourlyWeatherInfo = HourlyWeatherInfoAdapter()
        adapterDailyWeatherInfo = DailyWeatherInfoAdapter()
    }

    private fun initRecyclerView() {
        dataBinding.rvHourlyWeatherInfo.adapter = adapterHourlyWeatherInfo
        dataBinding.rvDailyWeatherInfo.adapter = adapterDailyWeatherInfo
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.weatherInfoStateFlow.collect { response ->
                when (response) {
                    is NetworkResult.Loading -> {
                        fragmentCommunicator?.showLoader()
                        showHideErrorUI(null)
                    }
                    is NetworkResult.Success -> {
                        fragmentCommunicator?.hideLoader()
                        showHideErrorUI(null)
                        dataBinding.cvHourlyWeatherInfo.show()
                        dataBinding.cvDailyWeatherInfo.show()
                        adapterHourlyWeatherInfo.setHourlyWeatherInfo(response.data.hourly)
                        adapterDailyWeatherInfo.setHourlyWeatherInfo(response.data.daily)
                    }

                    is NetworkResult.Error -> {
                        fragmentCommunicator?.hideLoader()
                        showHideErrorUI(response.exception)
                    }
                }
            }
        }
    }

    private fun getWeatherInfo() {
        val latitude = arguments?.getDouble(ARG_LATITUDE)
        val longitude = arguments?.getDouble(ARG_LONGITUDE)
        viewModel.getWeatherInfo(latitude!!, longitude!!)
    }

    private fun showHideErrorUI(exception: Throwable?) {
        exception?.let {
            dataBinding.viewEmpty.root.show()
            dataBinding.viewEmpty.tvTitle.text = it.localizedMessage
            dataBinding.viewEmpty.btnTryAgain.setOnClickListener {
                getWeatherInfo()
            }
        } ?: run {
            dataBinding.viewEmpty.root.gone()
        }
    }
}