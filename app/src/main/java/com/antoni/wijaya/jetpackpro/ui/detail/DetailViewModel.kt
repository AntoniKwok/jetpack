package com.antoni.wijaya.jetpackpro.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.vo.Resource

class DetailViewModel(
    private val movieRepository: MovieRepository?
) : ViewModel() {

    private val movieId = MutableLiveData<String>()

    fun getMovie(id : String) : LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId){ movieRepository?.getMovieDetail(id) }

    fun getTvShow(id : String) : LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(movieId){ movieRepository?.getTvShowDetail(id) }



}