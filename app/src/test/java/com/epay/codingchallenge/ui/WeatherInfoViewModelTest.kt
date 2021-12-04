package com.epay.codingchallenge.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.epay.codingchallenge.network.mapper.WeatherInfoResponseToUIModelMapper
import com.epay.codingchallenge.network.model.WeatherInfoUIModel
import com.epay.codingchallenge.network.repository.WeatherInfoRepository
import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.network.utils.RequestException
import com.epay.codingchallenge.testutil.MainCoroutineScopeRule
import com.epay.codingchallenge.testutil.TestUtils
import com.epay.codingchallenge.testutil.returns
import com.epay.codingchallenge.testutil.shouldEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor

/**
 * Created By Rafiqul Hasan
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherInfoViewModelTest {
    companion object {
        const val LATITUDE = -22.9028
        const val LONGITUDE = -43.2075

        const val WEATHER_INFO_HOURLY_LIST_SIZE = 48
        const val WEATHER_INFO_DAILY_LIST_SIZE = 5
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    lateinit var mockRepository: WeatherInfoRepository

    private lateinit var sutViewModel: WeatherInfoViewModel

    //data set
    private lateinit var responseWeatherInfo: WeatherInfoUIModel

    @Before
    fun setUp() {
        sutViewModel = WeatherInfoViewModel(mockRepository)

        val mapper = WeatherInfoResponseToUIModelMapper()
        responseWeatherInfo = mapper.map(TestUtils.getWeatherInfoTestData("weather_info.json"))
    }

    @Test
    fun `check argument pass correctly in getWeatherInfo fun`() {
        runBlocking {
            // Arrange
            successWeatherInfo()
            val acDouble = argumentCaptor<Double>()

            // Act
            sutViewModel.getWeatherInfo(LATITUDE, LONGITUDE)
            Mockito.verify(mockRepository).getWeatherInfo(acDouble.capture(), acDouble.capture())

            // Assert
            acDouble.allValues[0] shouldEqual LATITUDE
            acDouble.allValues[1] shouldEqual LONGITUDE
        }
    }

    @Test
    fun `get weather info data and it should return correct data`() {
        runBlocking {
            // Arrange
            successWeatherInfo()

            // Act
            sutViewModel.getWeatherInfo(LATITUDE, LONGITUDE)

            // Assert
            val response = sutViewModel.weatherInfoStateFlow.first()
            (response as NetworkResult.Success<WeatherInfoUIModel>).data.daily.size shouldEqual WEATHER_INFO_DAILY_LIST_SIZE
            response.data.hourly.size shouldEqual WEATHER_INFO_HOURLY_LIST_SIZE
            response.data.hourly[0].dateTime shouldEqual "02:00am\n12/05"
            response.data.hourly[47].dateTime shouldEqual "01:00am\n12/07"
            response.data.daily[4].dateTime shouldEqual "Wed, Dec 08"
        }
    }

    @Test
    fun `test get weather info failure`() {
        runBlocking {
            // Arrange
            failure()

            // Act
            sutViewModel.getWeatherInfo(LATITUDE, LONGITUDE)

            // Assert
            val response = sutViewModel.weatherInfoStateFlow.first()
            Assertions.assertThat((response as NetworkResult.Error).exception).isInstanceOf(
                RequestException::class.java
            )
            response.exception.message shouldEqual "Please check your network connection and try again."
        }
    }

    private fun successWeatherInfo() {
        runBlocking {
            mockRepository.getWeatherInfo(any(), any()) returns NetworkResult.Success(
                responseWeatherInfo
            )
        }
    }

    private fun failure() {
        runBlocking {
            mockRepository.getWeatherInfo(
                any(),
                any()
            ) returns NetworkResult.Error(RequestException("Please check your network connection and try again."))
        }
    }
}