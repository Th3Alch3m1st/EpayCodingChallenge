package com.epay.codingchallenge.ui.citysearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epay.codingchallenge.network.model.City
import com.epay.codingchallenge.network.utils.NetworkResult
import com.epay.codingchallenge.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Rafiqul Hasan
 */
@HiltViewModel
class CitySearchViewModel @Inject constructor(private val repository: CityRepository) :
    ViewModel() {
    private val _cities =
        MutableStateFlow<NetworkResult<List<City>>>(NetworkResult.Loading)
    val cities = _cities.asStateFlow()

    fun getCities() {
        viewModelScope.launch {
            _cities.emit(NetworkResult.Loading)
            val response = repository.getCities()
            _cities.emit(response)
        }
    }

}