package com.epay.codingchallenge.testutil

import com.epay.codingchallenge.network.model.WeatherInfoResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.IOException

/**
 * Created By Rafiqul Hasan
 */
object TestUtils {
    @Throws(IOException::class)
    private fun readFileToString(contextClass: Class<*>, fileName: String): String {
        contextClass.getResourceAsStream(fileName)
            ?.bufferedReader().use {
                val jsonString = it?.readText() ?: ""
                it?.close()
                return jsonString
            }
    }

    fun getWeatherInfoTestData(fileName: String): WeatherInfoResponse {
        val moshi = Moshi.Builder()
            .build()
        val jsonAdapter: JsonAdapter<WeatherInfoResponse> = moshi.adapter(WeatherInfoResponse::class.java)
        val jsonString = readFileToString(TestUtils::class.java, "/$fileName")
        return jsonAdapter.fromJson(jsonString)!!
    }
}