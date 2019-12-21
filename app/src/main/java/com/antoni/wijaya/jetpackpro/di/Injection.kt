package com.antoni.wijaya.jetpackpro.di

import android.app.Application
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.data.source.remote.repository.RemoteRepository
import com.antoni.wijaya.jetpackpro.utils.JsonHelper

class Injection {

    companion object{

        fun provideRepository(application: Application) : MovieRepository? {
            val remoteRepository = RemoteRepository.getInstance(JsonHelper(application))
            return MovieRepository.getInstance(remoteRepository)
        }

    }

}