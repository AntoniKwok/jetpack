package com.antoni.wijaya.jetpackpro.data.source.remote.response

import androidx.annotation.Nullable

class ApiResponse<T>(val statusResponse: StatusResponse, val body: T, val message: String?) {

    companion object {

        fun <T> success(@Nullable body: T): ApiResponse<T> {
            return ApiResponse(
                StatusResponse.SUCCESS,
                body,
                null
            )
        }

        fun <T> empty(body: T): ApiResponse<T> {
            return ApiResponse(
                StatusResponse.EMPTY,
                body,
                null
            )
        }

        fun <T> error(body: T): ApiResponse<T> {
            return ApiResponse(
                StatusResponse.ERROR,
                body,
                null
            )
        }


    }

}