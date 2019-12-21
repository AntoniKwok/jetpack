package com.antoni.wijaya.jetpackpro.data.source.remote.repository

import com.antoni.wijaya.jetpackpro.data.source.remote.response.MovieResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.TvShowResponse
import com.antoni.wijaya.jetpackpro.utils.JsonHelper

class RemoteRepository(private val jsonHelper: JsonHelper) {

    companion object {
        private var INSTANCE: RemoteRepository? = null
        fun getInstance(jsonHelper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(jsonHelper)
            }

            return INSTANCE
        }
    }

    fun getMovieData(): List<MovieResponse>? {
        return jsonHelper.getMovieData()
    }

    fun getTvShowData(): List<TvShowResponse>? {
        return jsonHelper.getTvShowData()
    }

}