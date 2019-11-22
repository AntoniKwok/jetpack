package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antoni.wijaya.jetpackpro.model.MovieEntity
import com.antoni.wijaya.jetpackpro.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovies() : ArrayList<MovieEntity>{
        return DataDummy.generateMovieDataDummy();
    }
}