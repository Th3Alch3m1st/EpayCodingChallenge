package com.epay.codingchallenge.network.mapper

/**
 * Created By Rafiqul Hasan
 */
interface Mapper<IN, OUT> {
    fun map(input: IN): OUT
}