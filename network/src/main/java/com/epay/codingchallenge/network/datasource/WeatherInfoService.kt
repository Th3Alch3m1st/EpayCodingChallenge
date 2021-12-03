package com.epay.codingchallenge.network.datasource

import com.epay.codingchallenge.network.model.WeatherInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created By Rafiqul Hasan
 */
interface WeatherInfoService {
    @GET("data/2.5/onecall")
    suspend fun getWeatherInfo(@QueryMap queryMap: Map<String, String>): Response<WeatherInfoResponse>
}