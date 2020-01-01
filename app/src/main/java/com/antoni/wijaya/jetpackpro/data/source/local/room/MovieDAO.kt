package com.antoni.wijaya.jetpackpro.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
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

    //Favorite
//
    @WorkerThread
    @Query("SELECT * FROM movies WHERE is_fav = 1")
    fun getFavoriteMovieData(): DataSource.Factory<Int, MovieEntity>

    @WorkerThread
    @Query("SELECT * FROM tvshows WHERE is_fav = 1")
    fun getFavoriteTvShowData(): DataSource.Factory<Int, TvShowEntity>

    @Query("UPDATE movies SET is_fav = 1 WHERE id = :id")
    fun insertFavoriteMovieData(id: String?)

    @Query("UPDATE movies SET is_fav = 0 WHERE id = :id")
    fun deleteFavoriteMovieData(id: String?)

    @Query("UPDATE tvshows SET is_fav = 1 WHERE id = :id")
    fun insertFavoriteTvShowData(id: String?)

    @Query("UPDATE tvshows SET is_fav = 0 WHERE id = :id")
    fun deleteFavoriteTvShowData(id: String?)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun checkFavoriteMovieData(id : String): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshows WHERE id = :id")
    fun checkFavoriteTvShowData(id : String): LiveData<TvShowEntity>

}