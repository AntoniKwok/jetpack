package com.antoni.wijaya.jetpackpro.ui.favorite.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.vo.Resource

open class FavoriteTvShowViewModel(private val movieRepository: MovieRepository? = null) : ViewModel() {

    fun getFavoriteTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>? = movieRepository?.getPagedFavoriteTvShowData()

}