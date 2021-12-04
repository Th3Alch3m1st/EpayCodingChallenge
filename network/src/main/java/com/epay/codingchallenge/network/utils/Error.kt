package com.epay.codingchallenge.network.utils

import com.squareup.moshi.JsonClass

/**
 * Created By Rafiqul Hasan
 */
@JsonClass(generateAdapter = true)
data class Error(
    val status: Int? = null,
    val error: String? = null,
    val message: String? = null
)