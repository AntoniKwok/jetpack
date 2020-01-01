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
    var id: String = "",
    private val movieRepository: MovieRepository?
) : ViewModel() {

    private val movieId = MutableLiveData<String>()

    fun getMovie(): LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { movieRepository?.getMovieDetail(id) }

    fun getTvShow(): LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(movieId) { movieRepository?.getTvShowDetail(id) }

    fun setMovieFavorite(id: String) {
        movieRepository?.setFavoriteMovie(id)
    }

    fun removeMovieFavorite(id: String) {
        movieRepository?.removeFavoriteMovie(id)
    }

    fun checkFavoriteMovies(id: String): LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { movieRepository?.checkFavoriteMovieData(id) }

    fun setTvShowFavorite(id: String) {
        movieRepository?.setFavoriteTvShow(id)
    }

    fun removeTvShowFavorite(id: String) {
        movieRepository?.removeFavoriteTvShow(id)
    }

    fun checkFavoriteTvShow(id: String): LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(movieId) { movieRepository?.checkFavoriteTvShowData(id) }

    fun setUsername(username: String) {
        movieId.value = username
    }


}