package com.antoni.wijaya.jetpackpro.data.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.vo.Resource

interface MovieDataSource {
    fun getMovieData(): LiveData<Resource<List<MovieEntity>>>
    fun getTvShowData(): LiveData<Resource<List<TvShowEntity>>>
    fun getMovieDetail(id : String): LiveData<Resource<MovieEntity>>
    fun getTvShowDetail(id : String): LiveData<Resource<TvShowEntity>>

    //favorite
    fun getPagedFavoriteMovieData() : LiveData<Resource<PagedList<MovieEntity>>>
    fun getPagedFavoriteTvShowData() : LiveData<Resource<PagedList<TvShowEntity>>>

//    fun getFavoriteTvShowData() : LiveData<Resource<TvShowEntity>>
//
    fun setFavoriteMovie(id: String)
    fun setFavoriteTvShow(id: String)
//
    fun removeFavoriteMovie(id: String)
    fun removeFavoriteTvShow(id: String)
//
    fun checkFavoriteMovieData(id : String) : LiveData<Resource<MovieEntity>>
    fun checkFavoriteTvShowData(id : String) : LiveData<Resource<TvShowEntity>>

}