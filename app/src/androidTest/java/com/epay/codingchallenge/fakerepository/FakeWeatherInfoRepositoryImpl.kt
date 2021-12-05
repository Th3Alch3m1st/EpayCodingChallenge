package com.epay.codingchallenge.fakerepository

import com.epay.codingchallenge.network.model.*
import com.epay.codingchallenge.network.repository.WeatherInfoRepository
import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.network.utils.RequestException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created By Rafiqul Hasan
 */
@Singleton
class FakeWeatherInfoRepositoryImpl @Inject constructor() : WeatherInfoRepository {
    companion object {
        const val MSG_ERROR = "Invalid Token"
    }

    var isTestError = false

    override suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double
    ): NetworkResult<WeatherInfoUIModel> {
        return if(isTestError){
            NetworkResult.Error(RequestException(message = MSG_ERROR))
        }else{
            NetworkResult.Success(getFakeWeatherInfo())
        }
    }

    private fun getFakeWeatherInfo(): WeatherInfoUIModel {
        return WeatherInfoUIModel(
            hourly = listOf(
                WeatherInfoHourly(
                    dateTime = "12:00PM\n12/05",
                    temp = 23.58f,
                    humidity = 81,
                    weather = WeatherInfoIcon("Rainy 1", "")
                ),
                WeatherInfoHourly(
                    dateTime = "01:00PM\n12/05",
                    temp = 24.58f,
                    humidity = 82,
                    weather = WeatherInfoIcon("Rainy 2", "")
                ),
                WeatherInfoHourly(
                    dateTime = "02:00PM\n12/05",
                    temp = 25.58f,
                    humidity = 83,
                    weather = WeatherInfoIcon("Rainy 3", "")
                ),
                WeatherInfoHourly(
                    dateTime = "03:00PM\n12/05",
                    temp = 25.58f,
                    humidity = 84,
                    weather = WeatherInfoIcon("Rainy 4", "")
                ),
                WeatherInfoHourly(
                    dateTime = "04:00PM\n12/05",
                    temp = 26.58f,
                    humidity = 85,
                    weather = WeatherInfoIcon("Rainy 5", "")
                )

            ),
            daily = listOf(
                WeatherInfoDaily(
                    dateTime = "Sun, Dec 05",
                    temp = Temperature(
                        min = 22.2f,
                        max = 28.2f
                    ),
                    humidity = 80,
                    weather = WeatherInfoIcon(
                        description = "Moderate Rain",
                        icon = ""
                    )
                ), WeatherInfoDaily(
                    dateTime = "Mon, Dec 06",
                    temp = Temperature(
                        min = 23.2f,
                        max = 29.2f
                    ),
                    humidity = 81,
                    weather = WeatherInfoIcon(
                        description = "Moderate Rain 1",
                        icon = ""
                    )
                ), WeatherInfoDaily(
                    dateTime = "Tue, Dec 07",
                    temp = Temperature(
                        min = 24.2f,
                        max = 30.2f
                    ),
                    humidity = 82,
                    weather = WeatherInfoIcon(
                        description = "Moderate Rain 2",
                        icon = ""
                    )
                ), WeatherInfoDaily(
                    dateTime = "Wed, Dec 08",
                    temp = Temperature(
                        min = 25.2f,
                        max = 31.2f
                    ),
                    humidity = 82,
                    weather = WeatherInfoIcon(
                        description = "Moderate Rain 3",
                        icon = ""
                    )
                ), WeatherInfoDaily(
                    dateTime = "Thu, Dec 09",
                    temp = Temperature(
                        min = 25.2f,
                        max = 31.2f
                    ),
                    humidity = 82,
                    weather = WeatherInfoIcon(
                        description = "Moderate Rain 4",
                        icon = ""
                    )
                )
            )
        )
    }

}