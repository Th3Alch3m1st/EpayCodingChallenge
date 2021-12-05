package com.epay.codingchallenge.testsuite

import com.epay.codingchallenge.ui.MainActivityTest
import com.epay.codingchallenge.ui.weatherinfo.WeatherInfoFragmentTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created By Rafiqul Hasan
 */
@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    WeatherInfoFragmentTest::class,
)
class TestSuiteCarInfoApp