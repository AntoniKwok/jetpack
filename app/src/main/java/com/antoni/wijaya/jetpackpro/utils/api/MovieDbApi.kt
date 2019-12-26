package com.antoni.wijaya.jetpackpro.utils.api

import androidx.lifecycle.LiveData
import retrofit2.http.Query
import com.antoni.wijaya.jetpackpro.BuildConfig
import com.antoni.wijaya.jetpackpro.data.source.remote.response.ApiResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.MovieResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbApi {
    @GET(BuildConfig.BASE_URL + "/movie/{movieId}")
    fun getDetailMovie(
        @Path("movieId") movieId: String,
        @Query("api_key") api: String = BuildConfig.API_KEY
    ): LiveData<ApiResponse<MovieResponse>>

    @GET(BuildConfig.BASE_URL + "/tv/{tvId}")
    fun getDetailTvShow(
        @Path("tvId") tvShowId: String,
        @Query("api_key") api: String = BuildConfig.API_KEY
    ): LiveData<ApiResponse<TvShowResponse>>
}