package com.antoni.wijaya.jetpackpro.data.source.local

import androidx.lifecycle.LiveData
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.local.room.MovieDAO

class LocalRepository(private val dao: MovieDAO) {

    companion object {
        private var INSTANCE: LocalRepository? = null
        fun getInstance(dao: MovieDAO): LocalRepository? {
            if (INSTANCE == null) {
                INSTANCE = LocalRepository(dao)
            }

            return INSTANCE
        }

    }

    fun getAllMovie(): LiveData<List<MovieEntity>> {
        return dao.getMovieData()
    }

    fun getAllTvShow(): LiveData<List<TvShowEntity>> {
        return dao.getTvShowData()
    }

    fun getDetailMovie(id: String): LiveData<MovieEntity> {
        return dao.getDetailMovieData(id)
    }

    fun getDetailTvShow(id: String): LiveData<TvShowEntity> {
        return dao.getDetailTvShowData(id)
    }

    fun insertMovie(movie: List<MovieEntity>) {
        dao.insertMovieData(movie)
    }

    fun insertTvShow(movie: List<TvShowEntity>) {
        dao.insertTvShowData(movie)
    }


}