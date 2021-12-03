package com.epay.codingchallenge.network

/**
 * Created By Rafiqul Hasan
 */
sealed class NetworkResultWithLoading<out T> {
    class Success<out T>(val data: T) : NetworkResultWithLoading<T>()
    class Error(val exception: Throwable) : NetworkResultWithLoading<Nothing>()
    object Loading: NetworkResultWithLoading<Nothing>()
}