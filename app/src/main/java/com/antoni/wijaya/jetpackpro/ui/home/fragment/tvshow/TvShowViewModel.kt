package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.model.MovieEntity
import com.antoni.wijaya.jetpackpro.utils.DataDummy

class TvShowViewModel : ViewModel() {

    private var tvShows : ArrayList<MovieEntity>? = null

    fun getTvShows(): ArrayList<MovieEntity> {
        if(tvShows==null){
            tvShows = DataDummy.generateTvShowDataDummy()
        }
        return tvShows ?: arrayListOf()
    }

}