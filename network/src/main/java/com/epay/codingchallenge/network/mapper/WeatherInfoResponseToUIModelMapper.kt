package com.epay.codingchallenge.network.mapper

import com.epay.codingchallenge.network.model.*

/**
 * Created By Rafiqul Hasan
 */
class WeatherInfoResponseToUIModelMapper :
    Mapper<WeatherInfoResponse, WeatherInfoUIModel> {
    override fun map(input: WeatherInfoResponse): WeatherInfoUIModel {
        return WeatherInfoUIModel(
            hourly = input.hourly?.map { hourly ->
                WeatherInfoHourly(
                    dateTime = hourly.dt ?: 0,
                    temp = hourly.temp?.toFloat() ?: 0.0f,
                    weather = WeatherInfoIcon(
                        description = hourly.weather?.getOrNull(0)?.description ?: "",
                        icon = hourly.weather?.getOrNull(0)?.icon ?: "",
                    )
                )
            } ?: mutableListOf(),
            daily = input.daily?.map { daily ->
                WeatherInfoDaily(
                    dateTime = daily.dt ?: 0,
                    temp = Temperature(
                        min = daily.temp?.min?.toFloat() ?: 0.0f,
                        max = daily.temp?.max?.toFloat() ?: 0.0f
                    ),
                    humidity = daily.humidity ?: 0,
                    weather = WeatherInfoIcon(
                        description = daily.weather?.getOrNull(0)?.description ?: "",
                        icon = daily.weather?.getOrNull(0)?.icon ?: ""
                    )
                )
            } ?: mutableListOf()
        )
    }
}