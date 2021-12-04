package com.epay.codingchallenge.network.repository

import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.network.model.WeatherInfoUIModel

/**
 * Created By Rafiqul Hasan
 */
interface WeatherInfoRepository {
    suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double
    ): NetworkResult<WeatherInfoUIModel>
}