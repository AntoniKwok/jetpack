package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.data.model.MovieValue
import com.antoni.wijaya.jetpackpro.utils.DataDummy

class TvShowViewModel : ViewModel() {

    private var tvShows : ArrayList<MovieValue>? = null

    fun getTvShows(): ArrayList<MovieValue> {
        if(tvShows==null){
            tvShows = DataDummy.generateTvShowDataDummy()
        }
        return tvShows ?: arrayListOf()
    }

}