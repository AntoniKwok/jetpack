package com.antoni.wijaya.jetpackpro.data.source.remote

import androidx.lifecycle.LiveData
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.vo.Resource

interface MovieDataSource {
    fun getMovieData(): LiveData<Resource<List<MovieEntity>>>
    fun getTvShowData(): LiveData<Resource<List<TvShowEntity>>>
    fun getMovieDetail(id : String): LiveData<Resource<MovieEntity>>
    fun getTvShowDetail(id : String): LiveData<Resource<TvShowEntity>>
}