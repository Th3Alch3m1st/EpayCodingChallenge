package com.epay.codingchallenge.network.utils

/**
 * Created By Rafiqul Hasan
 */

sealed class NetworkResult<out T> {
    class Success<out T>(val data: T) : NetworkResult<T>()
    class Error(val exception: Throwable) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}