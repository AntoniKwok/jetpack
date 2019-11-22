package com.antoni.wijaya.jetpackpro.ui.detail

import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.model.MovieEntity
import com.antoni.wijaya.jetpackpro.utils.DataDummy

class DetailViewModel : ViewModel() {

    fun getSelectedShow(type: String, showId: String): MovieEntity? {
        return if (type == "movie")
            DataDummy.getSelectedMovieData(showId)
        else
            DataDummy.getSelectedTvShowData(showId)

    }

}