package com.epay.codingchallenge.repository

import com.epay.codingchallenge.network.model.City
import com.epay.codingchallenge.network.utils.NetworkResult
import javax.inject.Inject

/**
 * Created By Rafiqul Hasan
 */
class CityRepositoryImpl @Inject constructor() : CityRepository {
    override suspend fun getCities(): NetworkResult<List<City>> {
        val list = mutableListOf<City>()
        return try {
            val path = "res/raw/cities.csv"
            this.javaClass.classLoader?.getResourceAsStream(path)
                ?.bufferedReader().use {
                    it?.readLines()?.forEach { string ->
                        val split = string.split(",")
                        list.add(City(split[0].toIntOrNull() ?: 0, split[1]))
                    }
                }
            list.removeAt(0)
            NetworkResult.Success(list)
        } catch (ex: Exception) {
            NetworkResult.Error(ex)
        }
    }
}