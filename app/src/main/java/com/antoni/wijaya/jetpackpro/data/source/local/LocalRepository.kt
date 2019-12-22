package com.antoni.wijaya.jetpackpro.data.source.local

import androidx.lifecycle.LiveData
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.local.room.MovieDAO

class LocalRepository(private val dao: MovieDAO) {

    companion object{
        private var INSTANCE: LocalRepository? = null
        fun getInstance(dao: MovieDAO) : LocalRepository?{
            if(INSTANCE == null){
                INSTANCE = LocalRepository(dao)
            }

            return INSTANCE
        }

    }

    fun getAllMovie() : LiveData<List<MovieEntity>>{
        return dao.getMovieData()
    }

    fun getAllTvShow() : LiveData<List<TvShowEntity>>{
        return dao.getTvShowData()
    }

}