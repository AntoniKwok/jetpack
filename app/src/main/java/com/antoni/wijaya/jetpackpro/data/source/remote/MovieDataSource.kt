package com.antoni.wijaya.jetpackpro.data.source.remote

import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity

interface MovieDataSource {
    fun getMovieData(): ArrayList<MovieEntity>?
    fun getTvShowData(): ArrayList<TvShowEntity>?
}