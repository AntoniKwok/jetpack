package com.antoni.wijaya.jetpackpro.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tvshows")
data class TvShowEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "desc")
    val desc: String? = "",

    @ColumnInfo(name = "rating")
    val rating: String? = "",

    @ColumnInfo(name = "genres")
    val genres: String? = "",

    @ColumnInfo(name = "released_date")
    val releaseDate: String? = "",

    @ColumnInfo(name = "image_url")
    val imageUrl: String? = ""

)