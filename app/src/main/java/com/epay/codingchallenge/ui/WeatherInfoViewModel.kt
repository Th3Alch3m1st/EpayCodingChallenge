package com.epay.codingchallenge.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epay.codingchallenge.network.NetworkResult
import com.epay.codingchallenge.network.model.WeatherInfoUIModel
import com.epay.codingchallenge.network.repository.WeatherInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Rafiqul Hasan
 */

@HiltViewModel
class WeatherInfoViewModel @Inject constructor(private val repository: WeatherInfoRepository) :
    ViewModel() {

    private val _weatherInfoStateFlow =
        MutableStateFlow<NetworkResult<WeatherInfoUIModel>>(NetworkResult.Loading)
    val weatherInfoStateFlow = _weatherInfoStateFlow.asStateFlow()

    fun getWeatherInfo(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            if (_weatherInfoStateFlow.value !is NetworkResult.Success) {
                _weatherInfoStateFlow.emit(NetworkResult.Loading)
                val response = repository.getWeatherInfo(latitude, longitude)
                _weatherInfoStateFlow.emit(response)
            }
        }
    }
}