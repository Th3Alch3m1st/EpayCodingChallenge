package com.epay.codingchallenge.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.epay.codingchallenge.R
import com.epay.codingchallenge.utils.DataBindingIdlingResource
import com.epay.codingchallenge.utils.monitorActivity
import com.epay.codingchallenge.utils.selectTabAtPosition
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith


/**
 * Created By Rafiqul Hasan
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class MainActivityTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun setUp() {
        hiltRule.inject()

        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

   /*
   *   Test spec
   *   name: Initial item loaded. UI should display weather list UI and error view will be invisible.
   *   steps:
   *       - [Action] launch activity
   *       - [Assert] it should show toolbar, toolbar tile "Simple Weather", tab layout and pager
   */
    @Test
    fun display_mainActivityOpen_opened() {
        //open activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        //verifying ui is present on screen
        onView(withId(R.id.root)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(hasDescendant(withText(R.string.simple_weather))))
        onView(withId(R.id.tab_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.pager)).check(matches(isDisplayed()))
    }

    /*
    *   Test spec
    *   name: after initial loading user should see three tab with three different city name.
    *   steps:
    *       - [Action] launch activity
    *       - [Assert] tab should display 3 following cities in tab title
    */
    @Test
    fun display_tabViewWithCorrectTabTitle() {
        //open activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        //verifying ui is present on screen
        onView(withId(R.id.tab_layout)).check(matches(hasDescendant(withText(R.string.city_rio_de_janeiro))))
        onView(withId(R.id.tab_layout)).check(matches(hasDescendant(withText(R.string.city_los_angeles))))
        onView(withId(R.id.tab_layout)).check(matches(hasDescendant(withText(R.string.city_beijing))))
    }

    /*
    *   Test spec
    *   name: user should be able to select different tab
    *   steps:
    *       - [Action] launch activity
    *       - [Assert] user should able to select different tab
    */
    @Test
    fun test_users_should_able_to_select_different_tab() {
        //open activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        //click to select tab
        onView(withId(R.id.tab_layout)).perform(selectTabAtPosition(0))
        Thread.sleep(1000)
        onView(withId(R.id.tab_layout)).perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        onView(withId(R.id.tab_layout)).perform(selectTabAtPosition(2))
        Thread.sleep(1000)
    }

    /*
     *   Test spec
     *   name: when user tap on a search icon it will open city search dialog. And when user should see loaded item to be searched
     *   steps:
     *       - [Assert] should see tabview
     *       - [Action] click on search action item
     *       - [Assert] search view is visible
     */
    @Test
    fun test_citySearchDialog_opened() {
        //open activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        //click to open city search view
        onView(withId(R.id.action_search)).perform(click())

        //verifying ui is present on screen
        onView(withId(R.id.rv_cities)).check(matches(isDisplayed()))
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_cancel)).check(matches(isDisplayed()))
        onView(withId(R.id.view)).check(matches(isDisplayed()))
    }

    /*
     *   Test spec
     *   name: when user tap on a search it will open filter dialog. And when user type "Dhaka" it should show one item
     *   steps:
     *       - [Assert] should see tabview
     *       - [Action] click on search action item
     *       - [Assert] search view is visible and type Dhaka
     *       - [Assert] list should show one item
     */
    @Test
    fun test_citySearchDialogDataFilterCorrectly() {
        //open activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.action_search)).perform(click())

        //creating scenario for empty response
        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(
                typeText("Dhaka"),
                pressImeActionButton()
            )
        Espresso.closeSoftKeyboard()

        Thread.sleep(1000)

        onView(withId(R.id.rv_cities)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            assertEquals(1, recyclerView.adapter?.itemCount)
        }
        onView(withId(R.id.rv_cities)).check(matches(hasDescendant(withText("Dhaka"))))
    }

    /*
     *   Test spec
     *   name: when user tap on a search it will open filter dialog. And when user type "asjdada" it should show nothing found UI
     *   steps:
     *       - [Assert] should see manufacturer item
     *       - [Action] click on search action item
     *       - [Assert] search view is visible and type "asjdada"
     *       - [Assert] view show display empty UI
     */
    @Test
    fun test_citySearchDialogEmptyResponse_showEmptyUI(){
        //open activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.action_search)).perform(click())

        //creating scenario for empty response
        onView(withId(androidx.appcompat.R.id.search_src_text))
            .perform(
                typeText("asjdada"),
                pressImeActionButton()
            )
        Espresso.closeSoftKeyboard()

        Thread.sleep(1000)

        //verify cases
        onView(withId(R.id.rv_cities)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            assertEquals(0, recyclerView.adapter?.itemCount)
        }
        onView(withId(R.id.view_empty)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(R.string.no_city_found)))
    }
}