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
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun test_display() {

        onView(withId(R.id.btnCamera)).check(matches(isDisplayed()))
        onView(withId(R.id.btnLeft)).check(matches(isDisplayed()))
        onView(withId(R.id.btnRight)).check(matches(isDisplayed()))
        onView(withId(R.id.btnFilter)).check(matches(isDisplayed()))
        onView(withId(R.id.ivMain)).check(matches(isDisplayed()))
    }


    @Test
    fun check_text_of_values() {
        onView(withId(R.id.btnCamera)).check(matches(withText("snap")))
        onView(withId(R.id.btnLeft)).check(matches(withText("left")))
        onView(withId(R.id.btnRight)).check(matches(withText("right")))
        onView(withId(R.id.btnFilter)).check(matches(withText("filter")))
        onView(withId(R.id.lblCaption)).check(matches(withText("Caption:")))
        onView(withId(R.id.lblCoord)).check(matches(withText("000, 000")))
        onView(withId(R.id.lblDate)).check(matches(withText("Date:")))
        onView(withId(R.id.lblLocation)).check(matches(withText("Location:")))
    }


    fun check_filter_function() {


    }

}