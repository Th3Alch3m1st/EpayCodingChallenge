package com.epay.codingchallenge.network

/**
 * Created By Rafiqul Hasan on 19/11/21
 * Brain Station 23
 */
sealed class NetworkResult<out T> {
    class Success<out T>(val data: T) : NetworkResult<T>()
    class Error(val exception: Throwable) : NetworkResult<Nothing>()
}