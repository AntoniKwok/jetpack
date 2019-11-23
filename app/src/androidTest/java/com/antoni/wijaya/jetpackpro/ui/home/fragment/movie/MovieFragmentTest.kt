package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.utils.RecyclerViewItemCountAssertion
import com.antoni.wijaya.testing.SingleFragmentActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieFragmentTest{

    @Rule @JvmField val activityRule = ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)
    val movieFragment = MovieFragment()

    @Before
    fun setUp(){
        activityRule.activity.setFragment(movieFragment)
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(RecyclerViewItemCountAssertion(11))
    }

}