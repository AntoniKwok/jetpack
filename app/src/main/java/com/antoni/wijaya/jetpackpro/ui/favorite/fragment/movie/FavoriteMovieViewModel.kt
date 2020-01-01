package com.antoni.wijaya.jetpackpro.ui.favorite.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.vo.Resource

open class FavoriteMovieViewModel(private val movieRepository: MovieRepository? = null) : ViewModel() {


    fun getFavoriteMovies(): LiveData<Resource<PagedList<MovieEntity>>>? = movieRepository?.getPagedFavoriteMovieData()

}