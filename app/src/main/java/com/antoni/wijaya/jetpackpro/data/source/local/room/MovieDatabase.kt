package com.antoni.wijaya.jetpackpro.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDAO
    companion object {

        private var INSTANCE: MovieDatabase? = null
        private val sLock = Object()

        fun getInstance(ctx: Context): MovieDatabase? {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        ctx.applicationContext,
                        MovieDatabase::class.java,
                        "MoviesDB.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}