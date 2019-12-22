package com.antoni.wijaya.jetpackpro.di

import android.app.Application
import com.antoni.wijaya.jetpackpro.data.source.local.LocalRepository
import com.antoni.wijaya.jetpackpro.data.source.local.room.MovieDatabase
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.data.source.remote.repository.RemoteRepository
import com.antoni.wijaya.jetpackpro.utils.AppExecutors
import com.antoni.wijaya.jetpackpro.utils.JsonHelper

class Injection {

    companion object{

        fun provideRepository(application: Application) : MovieRepository? {
            val remoteRepository = RemoteRepository.getInstance(JsonHelper(application))

            val database =MovieDatabase.getInstance(application)
            val localRepository = database?.movieDao()?.let { LocalRepository.getInstance(it) }

            val appExecutors = AppExecutors()
            return localRepository?.let {
                MovieRepository.getInstance(remoteRepository,
                    it, appExecutors)
            }
        }

    }

}