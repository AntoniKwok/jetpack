package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.data.model.MovieValue
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.utils.DataDummy

class TvShowViewModel(private val movieRepository: MovieRepository?) : ViewModel() {

    private var tvShows : ArrayList<TvShowEntity>? = null

    fun getTvShows(): ArrayList<TvShowEntity> {
        if(tvShows==null){
            if(movieRepository != null)
                tvShows = movieRepository.getTvShowData()
        }
        return tvShows ?: arrayListOf()
    }

}