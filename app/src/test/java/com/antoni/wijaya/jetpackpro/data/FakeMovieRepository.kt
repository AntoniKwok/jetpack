package com.antoni.wijaya.jetpackpro.data

import androidx.lifecycle.LiveData
import com.antoni.wijaya.jetpackpro.data.source.local.LocalRepository
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieDataSource
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.data.source.remote.NetworkBoundResource
import com.antoni.wijaya.jetpackpro.data.source.remote.repository.RemoteRepository
import com.antoni.wijaya.jetpackpro.data.source.remote.response.ApiResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.MovieResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.TvShowResponse
import com.antoni.wijaya.jetpackpro.utils.AppExecutors
import com.antoni.wijaya.jetpackpro.vo.Resource

class FakeMovieRepository(
    private val remoteRepository: RemoteRepository?,
    private val localRepository: LocalRepository,
    private val appExecutors: AppExecutors
) : MovieDataSource{
    companion object {
        @Volatile
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

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>>? =
                remoteRepository?.getMovieData()

            override fun saveCallResult(body: List<MovieResponse>) {
                val movieEntity = ArrayList<MovieEntity>()

                for (movie in body) {
                    movieEntity.add(
                        MovieEntity(
                            movie.id,
                            movie.title,
                            movie.desc,
                            movie.rating,
                            movie.genres,
                            movie.releasedDate,
                            movie.image
                        )
                    )
                }

                localRepository.insertMovie(movieEntity)

            }

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

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>>? =
                remoteRepository?.getTvShowData()

            override fun saveCallResult(body: List<TvShowResponse>) {
                val tvShowEntity = ArrayList<TvShowEntity>()

                for (movie in body) {
                    tvShowEntity.add(
                        TvShowEntity(
                            movie.id,
                            movie.title,
                            movie.desc,
                            movie.rating,
                            movie.genres,
                            movie.releasedDate,
                            movie.image
                        )
                    )
                }

                localRepository.insertTvShow(tvShowEntity)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(id: String): LiveData<Resource<MovieEntity>> {
        return object :
            NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localRepository.getDetailMovie(id)
            }

            override fun shouldFetch(data: MovieEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<MovieResponse>>? = null

            override fun saveCallResult(body: MovieResponse) {}

        }.asLiveData()
    }

    override fun getTvShowDetail(id: String): LiveData<Resource<TvShowEntity>> {
        return object :
            NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localRepository.getDetailTvShow(id)
            }

            override fun shouldFetch(data: TvShowEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>>? = null

            override fun saveCallResult(body: TvShowResponse) {}

        }.asLiveData()
    }

}