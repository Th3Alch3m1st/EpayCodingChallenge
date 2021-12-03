package com.epay.codingchallenge.network.datasource

import com.epay.codingchallenge.network.BuildConfig
import com.epay.codingchallenge.network.model.WeatherInfoResponse
import com.epay.codingchallenge.network.onResponse
import javax.inject.Inject

/**
 * Created By Rafiqul Hasan
 */
class WeatherInfoApi @Inject constructor(private val service: WeatherInfoService) {

    suspend fun getWeatherInfo(latitude: Double, longitude: Double): WeatherInfoResponse {
        val queryMap = mapOf(
            "lat" to "$latitude",
            "lon" to "$longitude",
            "exclude" to "minutely,current",
            "appid" to BuildConfig.AUTH_TOKEN
        )
        return service.getWeatherInfo(queryMap)
            .onResponse()
    }
}