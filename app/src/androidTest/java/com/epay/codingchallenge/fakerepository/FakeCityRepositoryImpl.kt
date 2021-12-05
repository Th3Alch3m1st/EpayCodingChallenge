package com.epay.codingchallenge.fakerepository

import com.epay.codingchallenge.network.model.City
import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.repository.CityRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created By Rafiqul Hasan
 */
@Singleton
class FakeCityRepositoryImpl @Inject constructor() : CityRepository {
    override suspend fun getCities(): NetworkResult<List<City>> {
        return NetworkResult.Success(getFakeCities())
    }


    fun getFakeCities() = listOf(
        City(1, "Dhaka"),
        City(2, "Munich"),
        City(3, "Hamburg"),
        City(4, "Uslar"),
        City(5, "Berlin"),
        City(6, "Troisdorf"),
        City(7, "Templin"),
        City(8, "Osnabrück"),
        City(9, "Olching"),
        City(10, "Papenburg")
    )
}