package com.epay.codingchallenge.network

import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created By Rafiqul Hasan
 */
const val SOCKET_TIME_OUT_EXCEPTION =
    "Please check your internet connection"
const val UNKNOWN_NETWORK_EXCEPTION =
    "Please check your network connection and try again."
const val CONNECT_EXCEPTION =
    "Please check your internet connection"
const val UNKNOWN_HOST_EXCEPTION =
    "Couldn't connect to the server at the moment. Please try again in a few minutes."

fun <T : Any> Response<T>.onResponse(): T {
    return if (this.isSuccessful) {
        if (this.body() != null) {
            this.body()!!
        } else {
            throw ApiException(this.code(), null, this.message())
        }
    } else {
        throw ApiException(this.code(), this.errorBody(), this.message())
    }
}

fun onException(exception: Exception): RequestException {
    return if (exception is ApiException) {
        ErrorHandler.parseRequestException(
            exception.statusCode,
            exception.errorBody,
            exception.message
        )
    } else {
        when (exception) {
            is ConnectException -> {
                RequestException(message = CONNECT_EXCEPTION)
            }
            is UnknownHostException -> {
                RequestException(message = UNKNOWN_HOST_EXCEPTION)
            }
            is SocketTimeoutException -> {
                RequestException(message = SOCKET_TIME_OUT_EXCEPTION)
            }
            is HttpException -> {
                RequestException(message = UNKNOWN_NETWORK_EXCEPTION)
            }
            else -> {
                RequestException(message = UNKNOWN_NETWORK_EXCEPTION)
            }
        }
    }
}