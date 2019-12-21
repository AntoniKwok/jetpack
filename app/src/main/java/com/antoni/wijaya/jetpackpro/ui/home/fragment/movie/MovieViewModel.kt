package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.data.model.MovieValue
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.utils.DataDummy

class MovieViewModel(private val movieRepository: MovieRepository?) : ViewModel() {

    private var movies : ArrayList<MovieEntity>? = null

    fun getMovies(): ArrayList<MovieEntity> {
        if(movies == null)
            if(movieRepository != null)
                movies = movieRepository.getMovieData()
        return movies ?: arrayListOf()
    }
}