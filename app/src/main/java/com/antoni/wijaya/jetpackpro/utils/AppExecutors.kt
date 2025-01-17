package com.antoni.wijaya.jetpackpro.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@VisibleForTesting
open class AppExecutors (
    private val diskIO : Executor,
    private val networkIO : Executor,
    private val mainThread : Executor
){

    companion object{
        const val THREAD_COUNT = 3
    }

    constructor() : this(
        DiskIOThreadExecutor(),
        Executors.newFixedThreadPool(THREAD_COUNT),
        MainThreadExecutor()
    )


    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO() : Executor{
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {

        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(p0: Runnable) {
            mainThreadHandler.post(p0)
        }

    }

}


