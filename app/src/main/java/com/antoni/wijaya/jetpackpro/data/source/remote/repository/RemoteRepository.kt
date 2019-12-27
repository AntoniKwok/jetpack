package com.antoni.wijaya.jetpackpro.data.source.remote.repository

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.antoni.wijaya.jetpackpro.data.source.remote.response.ApiResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.MovieResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.TvShowResponse
import com.antoni.wijaya.jetpackpro.utils.EspressoIdlingResource
import com.antoni.wijaya.jetpackpro.utils.JsonHelper

class RemoteRepository(private val jsonHelper: JsonHelper) {


    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS = 2000
        private var INSTANCE: RemoteRepository? = null
        fun getInstance(jsonHelper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(jsonHelper)
            }

            return INSTANCE
        }
    }

    fun getMovieData(): LiveData<ApiResponse<List<MovieResponse>>>? {
        EspressoIdlingResource.increment()
        val resultMovie: MutableLiveData<ApiResponse<List<MovieResponse>>> = MutableLiveData()

        val handler = Handler()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.getMovieData())
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }, SERVICE_LATENCY_IN_MILLIS.toLong())

        return resultMovie
    }

    fun getTvShowData(): LiveData<ApiResponse<List<TvShowResponse>>>? {
        EspressoIdlingResource.increment()
        val resultTvShow: MutableLiveData<ApiResponse<List<TvShowResponse>>> = MutableLiveData()

        val handler = Handler()

        handler.postDelayed({
            resultTvShow.value = ApiResponse.success(jsonHelper.getTvShowData())
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow)
                EspressoIdlingResource.decrement()

        }, SERVICE_LATENCY_IN_MILLIS.toLong())


        return resultTvShow
    }

}