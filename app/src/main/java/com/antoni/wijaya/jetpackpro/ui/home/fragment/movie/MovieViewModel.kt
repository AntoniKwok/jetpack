package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.model.MovieEntity
import com.antoni.wijaya.jetpackpro.utils.DataDummy

class MovieViewModel : ViewModel() {

    private var movies : ArrayList<MovieEntity>? = null

    fun getMovies(): ArrayList<MovieEntity> {
        if(movies == null)
            movies = DataDummy.generateMovieDataDummy()
        return movies ?: arrayListOf()
    }
}