package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.data.model.MovieValue
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.utils.DataDummy
import com.antoni.wijaya.jetpackpro.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository?) : ViewModel() {

    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>>? = movieRepository?.getTvShowData()

}