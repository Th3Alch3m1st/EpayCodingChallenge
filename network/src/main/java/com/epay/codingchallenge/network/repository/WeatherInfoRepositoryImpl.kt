package com.epay.codingchallenge.network.repository

import android.util.Log
import com.epay.codingchallenge.network.NetworkResult
import com.epay.codingchallenge.network.datasource.WeatherInfoApi
import com.epay.codingchallenge.network.mapper.Mapper
import com.epay.codingchallenge.network.model.WeatherInfoResponse
import com.epay.codingchallenge.network.model.WeatherInfoUIModel
import com.epay.codingchallenge.network.onException
import javax.inject.Inject

/**
 * Created By Rafiqul Hasan
 */
class WeatherInfoRepositoryImpl @Inject constructor(
    private val api: WeatherInfoApi,
    private val mapper: Mapper<WeatherInfoResponse, WeatherInfoUIModel>
) : WeatherInfoRepository {
    override suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double
    ): NetworkResult<WeatherInfoUIModel> {
        return try {
            val weatherInfoResponse = api.getWeatherInfo(latitude, longitude)
            NetworkResult.Success(mapper.map(weatherInfoResponse))
        } catch (ex: Exception) {
            val exception = onException(ex)
            NetworkResult.Error(exception)
        }
    }

}