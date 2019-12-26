package com.antoni.wijaya.jetpackpro.vo

import androidx.annotation.NonNull

class Resource<T>(
    val status: Status,
    val data: T?,
    private val message: String?
) {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(@NonNull data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        if (other == null || javaClass != other.javaClass)
            return false

        val resource = other as Resource<*>

        if (status !== resource.status)
            return false

        if (message != resource.message)
            return false

        return if (data != null) data == resource.data else resource.data == null

    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + data.hashCode()
        return result
    }

    override fun toString(): String {
        return "Resource{status=$status, message=' $message '\'', data=$data '}'"
    }


}