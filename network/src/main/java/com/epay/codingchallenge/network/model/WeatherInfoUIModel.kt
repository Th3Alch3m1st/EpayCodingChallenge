package com.epay.codingchallenge.network.model


/**
 * Created By Rafiqul Hasan
 */
data class WeatherInfoUIModel(
    val hourly: List<WeatherInfoHourly>,
    val daily:List<WeatherInfoDaily>
)

data class WeatherInfoHourly(
    val dateTime: String,
    val temp: Float,
    val humidity: Int,
    val weather: WeatherInfoIcon
)

data class WeatherInfoDaily(
    val dateTime: String,
    val temp: Temperature,
    val humidity: Int,
    val weather: WeatherInfoIcon
)

data class WeatherInfoIcon(
    val description: String,
    val icon: String
)

data class Temperature(
    val min: Float,
    val max: Float
)