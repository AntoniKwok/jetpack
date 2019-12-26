package com.antoni.wijaya.jetpackpro.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    var id: String = "",
    var title: String = "",
    var desc: String = "",
    var genres: String = "",
    var rating: String = "",
    var releasedDate: String = "",
    var image: String = ""
) : Parcelable