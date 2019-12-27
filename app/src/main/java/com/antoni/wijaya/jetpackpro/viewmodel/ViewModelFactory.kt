package com.antoni.wijaya.jetpackpro.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.di.Injection
import com.antoni.wijaya.jetpackpro.ui.detail.DetailViewModel
import com.antoni.wijaya.jetpackpro.ui.home.fragment.movie.MovieViewModel
import com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow.TvShowViewModel

class ViewModelFactory(private val movieRepository: MovieRepository? = null) :
    ViewModelProvider.NewInstanceFactory() {

    private var id = ""

    constructor(movieRepository: MovieRepository? = null, movieId: String = "") : this(
        movieRepository
    ) {
        id = movieId
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }
            return INSTANCE
        }
    }

    fun getInstanceDetail(application: Application): ViewModelFactory? {
        if (INSTANCE == null) {
            synchronized(ViewModelFactory::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(application), id)
                }
            }
        }
        return INSTANCE
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(
                movieRepository
            ) as T
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(
                movieRepository
            ) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(
                id, movieRepository
            ) as T
            else -> throw IllegalArgumentException(modelClass.name)
        }
    }

}