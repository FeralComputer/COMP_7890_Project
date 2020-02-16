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
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed


@RunWith(AndroidJUnit4ClassRunner::class)
class SearchActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)

    @Test
    fun onCreate() {
        onView(withId(R.id.search_LocationLabel)).check(matches(isDisplayed()))
        onView(withId(R.id.search_LocationX)).check(matches(isDisplayed()))
        onView(withId(R.id.search_search)).check(matches(isDisplayed()))
        onView(withId(R.id.title_search)).check(matches(ViewMatchers.withText("Search Library")))
    }


}