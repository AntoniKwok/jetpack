package com.antoni.wijaya.jetpackpro.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movies")
    fun getMovieData(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvshows")
    fun getTvShowData(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getDetailMovieData(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshows WHERE id = :id")
    fun getDetailTvShowData(id: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieData(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowData(movies: List<TvShowEntity>)
}