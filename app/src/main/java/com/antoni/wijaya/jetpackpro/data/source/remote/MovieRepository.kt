package com.antoni.wijaya.jetpackpro.data.source.remote

import androidx.lifecycle.LiveData
import com.antoni.wijaya.jetpackpro.data.source.local.LocalRepository
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.repository.RemoteRepository
import com.antoni.wijaya.jetpackpro.data.source.remote.response.MovieResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.TvShowResponse
import com.antoni.wijaya.jetpackpro.utils.AppExecutors
import com.antoni.wijaya.jetpackpro.vo.Resource

class MovieRepository(
    private val remoteRepository: RemoteRepository?,
    private val localRepository: LocalRepository,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    companion object {
        private var INSTANCE: MovieRepository? = null
        fun getInstance(
            remoteRepository: RemoteRepository?,
            localRepository: LocalRepository,
            appExecutors: AppExecutors
        ): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieRepository(remoteRepository, localRepository, appExecutors)
                    }
                }
            }

            return INSTANCE
        }
    }

    override fun getMovieData(): LiveData<Resource<List<MovieEntity>>> {

        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localRepository.getAllMovie()
            }

            override fun shouldFetch(data: List<MovieEntity>): Boolean {
                return data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>>? = null

            override fun saveCallResult(body: List<MovieResponse>) {}

        }.asLiveData()
    }

    override fun getTvShowData(): LiveData<Resource<List<TvShowEntity>>> {
        return object :
            NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localRepository.getAllTvShow()
            }

            override fun shouldFetch(data: List<TvShowEntity>): Boolean {
                return data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>>? = null

            override fun saveCallResult(body: List<TvShowResponse>) {}

        }.asLiveData()
    }


}