package com.epay.codingchallenge.network.mapper

import com.epay.codingchallenge.network.model.*
import com.epay.codingchallenge.network.testutil.shouldEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created By Rafiqul Hasan
 */
@RunWith(JUnit4::class)
class WeatherInfoResponseToUIModelMapperTest {

    private lateinit var sutMapper: WeatherInfoResponseToUIModelMapper

    @Before
    fun setUp() {
        sutMapper = WeatherInfoResponseToUIModelMapper()
    }

    @Test
    fun `if hourly object is null it should return empty list`() {
        val uiModel = sutMapper.map(WeatherInfoResponse(hourly = null))
        uiModel.hourly shouldEqual emptyList()
    }

    @Test
    fun `if daily object is null it should return empty list`() {
        val uiModel = sutMapper.map(WeatherInfoResponse(daily = null))
        uiModel.daily shouldEqual emptyList()
    }

    @Test
    fun `WeatherItem list in HourlyItem object should return WeatherInfoIcon object with correct data`() {
        val uiModel = sutMapper.map(
            WeatherInfoResponse(
                hourly = listOf(
                    HourlyItem(
                        weather = listOf(
                            WeatherItem(
                                icon = "010d",
                                description = "fuzzy clouds",
                                main = "Cloudy"
                            )
                        )
                    )
                )
            )
        )
        uiModel.hourly[0].weather.description shouldEqual "fuzzy clouds"
        uiModel.hourly[0].weather.icon shouldEqual "010d"
    }

    @Test
    fun `WeatherItem list in DailyItem object should return WeatherInfoIcon object with correct data`() {
        val uiModel = sutMapper.map(
            WeatherInfoResponse(
                daily = listOf(
                    DailyItem(
                        weather = listOf(
                            WeatherItem(
                                icon = "04d",
                                description = "broken clouds",
                                main = "Clouds"
                            )
                        )
                    )
                )
            )
        )
        uiModel.daily[0].weather.description shouldEqual "broken clouds"
        uiModel.daily[0].weather.icon shouldEqual "04d"
    }

    @Test
    fun `temp object in DailyItem object should return Temperature object with correct data`() {
        val uiModel = sutMapper.map(
            WeatherInfoResponse(
                daily = listOf(
                    DailyItem(
                        temp = Temp(
                            min = 0.1,
                            max = 10.0,
                            eve = 17.0,
                            night = 2.0
                        )
                    )
                )
            )
        )
        uiModel.daily[0].temp.min shouldEqual 0.1f
        uiModel.daily[0].temp.max shouldEqual 10.0f
    }
}