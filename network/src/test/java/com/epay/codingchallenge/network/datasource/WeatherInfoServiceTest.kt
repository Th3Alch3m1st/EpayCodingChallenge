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
class WeatherInfoServiceTest {
    companion object {
        const val LATITUDE = -22.9028
        const val LONGITUDE = -43.2075
        const val TIME_ZONE = "America/Sao_Paulo"
    }

    @get:Rule
    val mockWebServer = MockWebServer()

    private lateinit var queryMap: Map<String, String>
    private lateinit var sutWeatherInfoService: WeatherInfoService

    @Before
    fun setUp() {
        val moshi = Moshi.Builder()
            .build()

        queryMap = mapOf(
            "lat" to "$LATITUDE",
            "lon" to "$LONGITUDE",
            "exclude" to "minutely,current",
            "appid" to "78a07164952e030a671b9350f6"
        )

        sutWeatherInfoService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(getOkHttpClient())
            .build()
            .create(WeatherInfoService::class.java)
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get weather info and it should return correct response data`(){
        runBlocking {

            // Act
            mockWebServer.enqueue(TestUtils.mockResponse("weather_info.json"))
            val response = sutWeatherInfoService.getWeatherInfo(queryMap)

            // Assert
            response.body()?.lat shouldEqual LATITUDE
            response.body()?.lon shouldEqual LONGITUDE
            response.body()?.timezone shouldEqual TIME_ZONE
        }
    }


}