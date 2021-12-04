package com.epay.codingchallenge.network.mapper

import com.epay.codingchallenge.network.BuildConfig
import com.epay.codingchallenge.network.model.*
import com.epay.codingchallenge.network.utils.getDailyInfoEpochTimeToDateTime
import com.epay.codingchallenge.network.utils.getHourlyInfoEpochTimeToDateTime

/**
 * Created By Rafiqul Hasan
 */
class WeatherInfoResponseToUIModelMapper :
    Mapper<WeatherInfoResponse, WeatherInfoUIModel> {
    override fun map(input: WeatherInfoResponse): WeatherInfoUIModel {
        return WeatherInfoUIModel(
            hourly = input.hourly?.map { hourly ->
                WeatherInfoHourly(
                    dateTime = getHourlyInfoEpochTimeToDateTime(hourly.dt ?: 0),
                    temp = hourly.temp?.toFloat() ?: 0.0f,
                    humidity = hourly.humidity ?: 0,
                    weather = WeatherInfoIcon(
                        description = hourly.weather?.getOrNull(0)?.description ?: "",
                        icon = if (!hourly.weather?.getOrNull(0)?.icon.isNullOrEmpty())
                            "${BuildConfig.ICON_URL}/${hourly.weather?.getOrNull(0)?.icon}@2x.png"
                        else ""
                    )
                )
            } ?: mutableListOf(),
            daily = input.daily?.take(5)?.map { daily ->
                WeatherInfoDaily(
                    dateTime = getDailyInfoEpochTimeToDateTime(daily.dt ?: 0),
                    temp = Temperature(
                        min = daily.temp?.min?.toFloat() ?: 0.0f,
                        max = daily.temp?.max?.toFloat() ?: 0.0f
                    ),
                    humidity = daily.humidity ?: 0,
                    weather = WeatherInfoIcon(
                        description = daily.weather?.getOrNull(0)?.description ?: "",
                        icon = if (!daily.weather?.getOrNull(0)?.icon.isNullOrEmpty())
                            "${BuildConfig.ICON_URL}/${daily.weather?.getOrNull(0)?.icon}@2x.png"
                        else ""
                    )
                )
            } ?: mutableListOf()
        )
    }
}