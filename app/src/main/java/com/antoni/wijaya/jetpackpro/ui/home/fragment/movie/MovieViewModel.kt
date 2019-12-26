package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.vo.Resource

open class MovieViewModel(private val movieRepository: MovieRepository?) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>>? = movieRepository?.getMovieData()
}