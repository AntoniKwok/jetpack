package com.antoni.wijaya.jetpackpro.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieValue(
    var id : String,
    var title: String,
    var desc: String,
    var genres: String,
    var rating: String,
    var releasedDate: String,
    var image: Int
) : Parcelable