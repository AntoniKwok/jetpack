package com.antoni.wijaya.jetpackpro.ui.detail

import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.data.model.MovieValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private lateinit var dummyShow: MovieValue

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        dummyShow = MovieValue(
            "424694",
            "Bohemian Rhapsody",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock \\'n\\' roll band Queen in 1970. Hit songs become instant classics. When Mercury\\'s increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "Drama, Music",
            "80",
            "October 24, 2018",
            R.drawable.poster_bohemian
        )
    }

    @Test
    fun getMovies() {
        val show = viewModel.getSelectedShow("movie", "424694")
        assertNotNull(show)
        assertEquals(dummyShow.id, show?.id)
        assertEquals(dummyShow.title, show?.title)
        assertEquals(dummyShow.desc, show?.desc)
        assertEquals(dummyShow.genres, show?.genres)
        assertEquals(dummyShow.rating, show?.rating)
        assertEquals(dummyShow.image, show?.image)
    }


}