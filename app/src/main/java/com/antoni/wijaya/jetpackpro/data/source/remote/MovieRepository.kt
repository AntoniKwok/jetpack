package com.antoni.wijaya.jetpackpro.data.source.remote

import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.repository.RemoteRepository
import com.antoni.wijaya.jetpackpro.data.source.remote.response.MovieResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.TvShowResponse

class MovieRepository(private val remoteRepository: RemoteRepository?) : MovieDataSource {

    companion object {
        private var INSTANCE: MovieRepository? = null
        fun getInstance(remoteRepository: RemoteRepository?): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieRepository(remoteRepository)
                    }
                }
            }

            return INSTANCE
        }
    }

    override fun getMovieData(): ArrayList<MovieEntity>? {
        val movieData: List<MovieResponse>? = remoteRepository?.getMovieData()
        val movies: ArrayList<MovieEntity> = arrayListOf()

        if (movieData != null)
            for (i in movieData.indices) {
                val response = movieData[i]
                val entity = MovieEntity(
                    response.id,
                    response.title,
                    response.desc,
                    response.rating,
                    response.genres,
                    response.releasedDate,
                    response.image
                )

                movies.add(entity)
            }

        return movies
    }

    override fun getTvShowData(): ArrayList<TvShowEntity>? {
        val tvShowData: List<TvShowResponse>? = remoteRepository?.getTvShowData()
        val tvShows: ArrayList<TvShowEntity> = arrayListOf()

        if (tvShowData != null)
            for (i in tvShowData.indices) {
                val response = tvShowData[i]
                val entity = TvShowEntity(
                    response.id,
                    response.title,
                    response.desc,
                    response.rating,
                    response.genres,
                    response.releasedDate,
                    response.image
                )

                tvShows.add(entity)
            }

        return tvShows
    }

}