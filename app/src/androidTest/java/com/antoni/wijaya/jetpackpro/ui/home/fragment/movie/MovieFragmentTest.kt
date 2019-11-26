package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.utils.RecyclerViewItemCountAssertion
import com.antoni.wijaya.testing.SingleFragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)
    private val movieFragment = MovieFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(movieFragment)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie))
            .check(RecyclerViewItemCountAssertion(11))
    }
}