package com.epay.codingchallenge.ui.weatherinfo

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.epay.codingchallenge.R
import com.epay.codingchallenge.fakerepository.FakeWeatherInfoRepositoryImpl
import com.epay.codingchallenge.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import javax.inject.Inject


/**
 * Created By Rafiqul Hasan
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class WeatherInfoFragmentTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val taskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fakeRepositoryImpl: FakeWeatherInfoRepositoryImpl

    private lateinit var bundle: Bundle

    @Before
    fun setUp() {
        hiltRule.inject()

        bundle = Bundle().apply {
            putDouble(WeatherInfoFragment.ARG_LATITUDE, -22.90278)
            putDouble(WeatherInfoFragment.ARG_LONGITUDE, -43.2075)
        }
    }

    /*
    *   Test spec
    *   name: Initial item loaded. UI should display weather list UI and error view will be invisible.
    *   steps:
    *       - [Action] launch fragment
    *       - [Assert] it should show data
    *       - [Assert] Error UI should be invisible
    */
    @Test
    fun test_initialView_displayed() {
        //open fragment
        val mockNavController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<WeatherInfoFragment>(
            bundle,
            R.style.Theme_EpayCodingChallenge
        ) {
            viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    Navigation.setViewNavController(requireView(), mockNavController)
                }
            }
        }

        //verifying cases
        onView(withId(R.id.rv_hourly_weather_info)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_daily_weather_info)).check(matches(isDisplayed()))
        onView(withId(R.id.view_empty)).check(matches(not(isDisplayed())))
    }

    /*
     *   Test spec
     *   name: during initial loading, error may happen. UI should display error reason with retry button.
     *   steps:
     *       - [Action] mock error scenario by setting testError to true
     *       - [Assert] it should show error UI
     */
    @Test
    fun test_errorResponse_displayed() {
        //open fragment
        fakeRepositoryImpl.isTestError = true

        val mockNavController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<WeatherInfoFragment>(
            bundle,
            R.style.Theme_EpayCodingChallenge
        ) {
            viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    Navigation.setViewNavController(requireView(), mockNavController)
                }
            }
        }

        //verifying cases
        Thread.sleep(200)
        onView(withId(R.id.view_empty)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title))
            .check(matches(withText(FakeWeatherInfoRepositoryImpl.MSG_ERROR)))
    }

    /*
     *   Test spec
     *   name: during initial loading, error may happen. UI should display error reason with retry button. Click on retry should fetch data again.
     *   steps:
     *       - [Action] mock error scenario by setting testError to true
     *       - [Assert] it should show error UI
     *       - [Action] setting testError to false
     *       - [Action] click on try again button
     *       - [Assert] error ui should be gone and list should be visible
     */
    @Test
    fun test_errorResponse_displayed_retry_should_fetch_data() {
        //open fragment
        fakeRepositoryImpl.isTestError = true
        val mockNavController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<WeatherInfoFragment>(
            bundle,
            R.style.Theme_EpayCodingChallenge
        ) {
            viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    Navigation.setViewNavController(requireView(), mockNavController)
                }
            }
        }

        fakeRepositoryImpl.isTestError = false
        onView(withId(R.id.btn_try_again)).perform(ViewActions.click())

        //verifying cases
        Thread.sleep(200)
        onView(withId(R.id.rv_hourly_weather_info)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_daily_weather_info)).check(matches(isDisplayed()))

    }

    /*
    *   Test spec
    *   name: Initial item loaded. UI should display 5 in daily and hourly weather info section
    *   steps:
    *       - [Action] launch fragment
    *       - [Assert] it should show 5 items in hourly weather info recyclerview
    *       - [Assert] it should show 5 items in daily weather info recyclerview
    */
    @Test
    fun test_itemLoaded_rightItemCount() {
        //open fragment
        val mockNavController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<WeatherInfoFragment>(bundle, R.style.Theme_EpayCodingChallenge) {
            viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    Navigation.setViewNavController(requireView(), mockNavController)
                }
            }
        }


        //verifying itemCount is correct
        Thread.sleep(200)
        onView(withId(R.id.rv_hourly_weather_info)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            assertEquals(5, recyclerView.adapter?.itemCount)
        }
        onView(withId(R.id.rv_daily_weather_info)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            assertEquals(5, recyclerView.adapter?.itemCount)
        }
    }
}