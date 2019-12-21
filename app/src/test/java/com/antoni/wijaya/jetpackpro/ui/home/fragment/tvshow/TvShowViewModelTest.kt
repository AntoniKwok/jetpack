package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest{

    private lateinit var viewModel : TvShowViewModel

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShow(){
        val tvShows= viewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(11, tvShows.size)
    }

}