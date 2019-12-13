package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.data.model.MovieValue
import com.antoni.wijaya.jetpackpro.utils.DataDummy

class MovieViewModel : ViewModel() {

    private var movies : ArrayList<MovieValue>? = null

    fun getMovies(): ArrayList<MovieValue> {
        if(movies == null)
            movies = DataDummy.generateMovieDataDummy()
        return movies ?: arrayListOf()
    }
}