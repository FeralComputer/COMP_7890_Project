package com.example.comp7855_project

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Test
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * TESTS THE DISPLAY OF ACTIVITY MAIN TO SEE IF ALL UI ELEMENTS ARE DISPLAYED
     * MORE CRITICAL WHEN UTILIZING FIREBASE OR MULTIPLE DEVICES TO SEE IF OUR UI IS DISPLAYED
     * CORRECTLY
     */
    @Test
    fun test_display() {
        onView(withId(R.id.btnCamera)).check(matches(isDisplayed()))
        onView(withId(R.id.btnLeft)).check(matches(isDisplayed()))
        onView(withId(R.id.btnRight)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFilter)).check(matches(isDisplayed()))
        onView(withId(R.id.ivMain)).check(matches(isDisplayed()))
    }


    /**
     * CHECK TO SEE IF ALL UI ELEMENTS ARE DISPLAYING THE CORRECT TEXT INFORMATION
     */
    @Test
    fun check_text_of_values() {
        onView(withId(R.id.btnCamera)).check(matches(withText("snap")))
        onView(withId(R.id.btnLeft)).check(matches(withText("left")))
        onView(withId(R.id.btnRight)).check(matches(withText("right")))
        onView(withId(R.id.btnFilter)).check(matches(withText("Search")))
        onView(withId(R.id.lblCaption)).check(matches(withText("Caption:")))
        onView(withId(R.id.lblDate)).check(matches(withText("Date:")))
        onView(withId(R.id.lblLocation)).check(matches(withText("Location:")))
    }

    /**
     * TEST TO SEE IF THE FILTER FUNCTION RETURNS THE CORRECT FILTER VALUES
     */
    @Test
    fun check_filter_function() {
        onView(withId(R.id.btnFilter)).perform(click())
        onView(withId(R.id.activity_search)).check(matches(isDisplayed()))
        onView(withId(R.id.search_fromYear)).perform(typeText("2019"))
        onView(withId(R.id.search_toYear)).perform(typeText("2020"))
        onView(withId(R.id.search_fromMonth)).perform(typeText("01"))
        onView(withId(R.id.search_toMonth)).perform(typeText("02"))
        onView(withId(R.id.search_fromDay)).perform(typeText("12"))
        onView(withId(R.id.search_toDay)).perform(typeText("30"), closeSoftKeyboard())
        onView(withId(R.id.search_cancel)).perform(click())
    }

    /**
     * UI TEST OF LOCATION SEARCH
     */
    @Test
    fun automation_location_based_search() {
        onView(withId(R.id.btnFilter)).perform(click())
        onView(withId(R.id.activity_search)).check(matches(isDisplayed()))
        onView(withId(R.id.search_LocationLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.search_LocationX)).perform(typeText("49"))
        onView(withId(R.id.search_LocationY)).perform(typeText("-123"))
        onView(withId(R.id.search_Radius)).perform(typeText("20"),closeSoftKeyboard())
        onView(withId(R.id.search_cancel)).perform(click())
        onView(withId(R.id.lblCoord)).check(matches(withText("49, -123")))

    }
}