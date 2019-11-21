package com.antoni.wijaya.jetpackpro.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var title: String,
    var desc: String,
    var genres: String,
    var rating: String,
    var released_date: String,
    var image: Int
) : Parcelable