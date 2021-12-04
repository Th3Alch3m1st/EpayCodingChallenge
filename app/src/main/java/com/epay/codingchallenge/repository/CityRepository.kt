package com.epay.codingchallenge.repository

import com.epay.codingchallenge.network.model.City
import com.epay.codingchallenge.network.utils.NetworkResult

/**
 * Created By Rafiqul Hasan
 */
interface CityRepository {
    suspend fun getCities(): NetworkResult<List<City>>
}