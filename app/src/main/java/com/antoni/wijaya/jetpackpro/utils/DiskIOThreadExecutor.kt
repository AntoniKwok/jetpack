package com.antoni.wijaya.jetpackpro.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DiskIOThreadExecutor(
    private val diskIO : Executor = Executors.newSingleThreadExecutor()
) : Executor{
    override fun execute(p0: Runnable) {
        diskIO.execute(p0)
    }

}