package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest{

    private lateinit var viewModel : MovieViewModel

    @Before
    fun setUp(){
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies(){
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(11, movies.size)
    }

}