package com.epay.codingchallenge.network.repository

import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.network.utils.RequestException
import com.epay.codingchallenge.network.datasource.WeatherInfoApi
import com.epay.codingchallenge.network.datasource.WeatherInfoServiceTest
import com.epay.codingchallenge.network.mapper.WeatherInfoResponseToUIModelMapper
import com.epay.codingchallenge.network.model.WeatherInfoUIModel
import com.epay.codingchallenge.network.testutil.TestUtils
import com.epay.codingchallenge.network.testutil.returns
import com.epay.codingchallenge.network.testutil.shouldEqual
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor

/**
 * Created By Rafiqul Hasan
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherInfoRepositoryImplTest{
    companion object{
        const val WEATHER_INFO_HOURLY_LIST_SIZE = 48
        const val WEATHER_INFO_DAILY_LIST_SIZE = 8
    }
    @Mock
    lateinit var mockApi: WeatherInfoApi

    private lateinit var sutRepository: WeatherInfoRepository

    @Before
    fun setUp() {
        sutRepository = WeatherInfoRepositoryImpl(mockApi,WeatherInfoResponseToUIModelMapper())
    }

    @Test
    fun `check argument pass correctly in getWeatherInfo fun`() {
        runBlocking {
            // Arrange
            successWeatherInfo()
            val acDouble = argumentCaptor<Double>()

            // Act
            sutRepository.getWeatherInfo(
                WeatherInfoServiceTest.LATITUDE,
                WeatherInfoServiceTest.LONGITUDE
            )
            Mockito.verify(mockApi).getWeatherInfo(acDouble.capture(), acDouble.capture())

            // Assert
            acDouble.allValues[0] shouldEqual WeatherInfoServiceTest.LATITUDE
            acDouble.allValues[1] shouldEqual WeatherInfoServiceTest.LONGITUDE
        }
    }

    @Test
    fun `get weather info data and it should return correct data`() {
        runBlocking {
            // Arrange
            successWeatherInfo()

            // Act
            val response = sutRepository.getWeatherInfo(
                WeatherInfoServiceTest.LATITUDE,
                WeatherInfoServiceTest.LONGITUDE
            )
            // Assert
            (response as NetworkResult.Success<WeatherInfoUIModel>).data.daily.size shouldEqual WEATHER_INFO_DAILY_LIST_SIZE
            response.data.hourly.size shouldEqual WEATHER_INFO_HOURLY_LIST_SIZE
            response.data.hourly[0].dateTime shouldEqual 1638525600
            response.data.hourly[47].dateTime shouldEqual 1638694800
            response.data.daily[7].dateTime shouldEqual 1639144800
        }
    }

    @Test
    fun `test get built date failure`() {
        runBlocking {
            // Arrange
            failure()

            // Act
            val response = sutRepository.getWeatherInfo(
                WeatherInfoServiceTest.LATITUDE,
                WeatherInfoServiceTest.LONGITUDE
            )

            // Assert
            Assertions.assertThat((response as NetworkResult.Error).exception).isInstanceOf(
                RequestException::class.java)
            response.exception.message shouldEqual "Please check your network connection and try again."
        }
    }

    private fun successWeatherInfo() {
        runBlocking {
            val testData = TestUtils.getWeatherInfoTestData("weather_info.json")
            mockApi.getWeatherInfo(any(), any()) returns testData
        }
    }

    private fun failure() {
        runBlocking {
            BDDMockito.given(
                mockApi.getWeatherInfo(any(), any())
            ).willAnswer {
                throw RequestException("Please check your network connection and try again.")
            }
        }
    }
}