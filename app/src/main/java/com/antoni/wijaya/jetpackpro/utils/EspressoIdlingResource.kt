package com.antoni.wijaya.jetpackpro.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

class EspressoIdlingResource {

    companion object{
        const val RESOURCE = "GLOBAL"
        val countingIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment(){
            countingIdlingResource.increment()
        }

        fun decrement(){
            countingIdlingResource.decrement()
        }

        fun getEspressoIdlingResource() : IdlingResource{
            return countingIdlingResource
        }
    }

}