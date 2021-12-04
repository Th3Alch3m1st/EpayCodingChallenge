package com.epay.codingchallenge.ui.citysearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.epay.codingchallenge.network.model.City
import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.network.utils.RequestException
import com.epay.codingchallenge.repository.CityRepository
import com.epay.codingchallenge.testutil.MainCoroutineScopeRule
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
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created By Rafiqul Hasan
 */
@RunWith(MockitoJUnitRunner::class)
class CitySearchViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    lateinit var mockRepository: CityRepository

    private lateinit var sutViewModel: CitySearchViewModel

    //data set
    private lateinit var listCity: List<City>

    @Before
    fun setUp() {
        sutViewModel = CitySearchViewModel(mockRepository)
        listCity = listOf(
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

    @Test
    fun `get Cities and it should return correct data`() {
        runBlocking {
            // Arrange
            success()

            // Act
            sutViewModel.getCities()

            // Assert
            val response = sutViewModel.cities.first()
            (response as NetworkResult.Success<List<City>>).data.size shouldEqual 10
            response.data[0].cityName shouldEqual "Dhaka"
            response.data[9].cityName shouldEqual "Papenburg"
        }
    }

    @Test
    fun `test get cities failure`() {
        runBlocking {
            // Arrange
            failure()

            // Act
            sutViewModel.getCities()

            // Assert
            val response = sutViewModel.cities.first()
            Assertions.assertThat((response as NetworkResult.Error).exception).isInstanceOf(
                RequestException::class.java
            )
            response.exception.message shouldEqual "Can't parse data"
        }
    }

    private fun success() {
        runBlocking {
            mockRepository.getCities() returns NetworkResult.Success(listCity)
        }
    }

    private fun failure() {
        runBlocking {
            mockRepository.getCities() returns NetworkResult.Error(RequestException("Can't parse data"))
        }
    }
}