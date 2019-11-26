package com.antoni.wijaya.jetpackpro.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.utils.FakeData
import org.junit.Rule
import org.junit.Test

class DetailActivityTest {

    private val movieDummy = FakeData.generateMovieDataDummy()[0]

    @Rule
    @JvmField
    val activityRule = object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {

        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(DetailActivity.ID, movieDummy.id)
            result.putExtra(DetailActivity.TYPE, "movie")
            return result
        }
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.txt_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_toolbar)).check(matches(withText(movieDummy.title)))

        onView(withId(R.id.txt_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_title)).check(matches(withText(movieDummy.title)))

        onView(withId(R.id.txt_date)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_date)).check(matches(withText(movieDummy.released_date)))

        onView(withId(R.id.txt_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_genre)).check(matches(withText(movieDummy.genres)))

        onView(withId(R.id.txt_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_desc)).check(matches(withText(movieDummy.desc)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(isDisplayed()))

    }
}