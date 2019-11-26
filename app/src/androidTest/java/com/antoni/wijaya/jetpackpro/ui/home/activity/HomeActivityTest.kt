package com.antoni.wijaya.jetpackpro.ui.home.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.antoni.wijaya.jetpackpro.R
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Test
    fun intentToDetailActivity() {
        onView(withId(R.id.nav_view))
            .check(matches(isDisplayed()))

        onView(withId(R.id.navigation_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        //go to detail
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        //back to home activity
        pressBack()

        onView(withId(R.id.navigation_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tvshow)).perform(click())

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

    }

}