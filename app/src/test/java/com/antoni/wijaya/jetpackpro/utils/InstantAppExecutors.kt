package com.antoni.wijaya.jetpackpro.utils

import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {

    companion object{
        private var instant = Executor{it.run()}
    }
}