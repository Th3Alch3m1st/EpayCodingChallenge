package com.epay.codingchallenge.network.datasource

import com.epay.codingchallenge.network.testutil.TestUtils
import com.epay.codingchallenge.network.testutil.TestUtils.getOkHttpClient
import com.epay.codingchallenge.network.testutil.shouldEqual
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created By Rafiqul Hasan
 */
@RunWith(JUnit4::class)
class WeatherInfoApiTest {
    @get:Rule
    val mockWebServer = MockWebServer()

    private lateinit var queryMap: Map<String, String>
    private lateinit var service: WeatherInfoService

    private lateinit var sutWeatherInfoApi: WeatherInfoApi

    @Before
    fun setUp() {
        val moshi = Moshi.Builder()
            .build()

        queryMap = mapOf(
            "lat" to "${WeatherInfoServiceTest.LATITUDE}",
            "lon" to "${WeatherInfoServiceTest.LONGITUDE}",
            "exclude" to "minutely,current",
            "appid" to "78a07164952e030a671b9350f6"
        )

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(getOkHttpClient())
            .build()
            .create(WeatherInfoService::class.java)

        sutWeatherInfoApi = WeatherInfoApi(service)
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get weather info and it should return correct data`() {
        runBlocking {
            // Arrange
            mockWebServer.enqueue(TestUtils.mockResponse("weather_info.json"))

            // Act
            val response = sutWeatherInfoApi.getWeatherInfo(
                WeatherInfoServiceTest.LATITUDE,
                WeatherInfoServiceTest.LONGITUDE
            )

            // Assert
            response.lat shouldEqual WeatherInfoServiceTest.LATITUDE
            response.lon shouldEqual WeatherInfoServiceTest.LONGITUDE
            response.timezone shouldEqual WeatherInfoServiceTest.TIME_ZONE
        }
    }
}