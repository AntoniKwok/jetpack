package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.model.MovieEntity
import com.antoni.wijaya.jetpackpro.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShows(): ArrayList<MovieEntity> {
        return DataDummy.generateTvShowDataDummy()
    }

}