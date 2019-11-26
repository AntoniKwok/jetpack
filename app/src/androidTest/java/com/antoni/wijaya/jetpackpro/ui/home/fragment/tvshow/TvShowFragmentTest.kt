package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.utils.RecyclerViewItemCountAssertion
import com.antoni.wijaya.testing.SingleFragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TvShowFragmentTest {

    @Rule @JvmField var activityRule =
        ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)
    private val tvShowFragment = TvShowFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(tvShowFragment)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .check(RecyclerViewItemCountAssertion(11))
    }

}