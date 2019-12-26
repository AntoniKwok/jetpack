package com.antoni.wijaya.jetpackpro.utils

import android.app.Application
import com.antoni.wijaya.jetpackpro.data.source.remote.response.MovieResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val application: Application) {

    private fun parsingFileToString(filename: String): String? {
        return try {
            val `is` = application.assets.open(filename)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }


    fun getMovieData(): List<MovieResponse> {
        var movieResponse: MovieResponse?
        val list = ArrayList<MovieResponse>()

        try {
            val obj = JSONObject(parsingFileToString("Movie.json").toString())
            val listData = obj.getJSONArray("results")

            for (i in 0 until listData.length()) {
                val movie = listData.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val desc = movie.getString("desc")
                val genre = movie.getString("genres")
                val rating = movie.getString("rating")
                val releasedDate = movie.getString("released_date")
                val imageUrl = movie.getString("image_url")

                movieResponse =
                    MovieResponse(id, title, desc, genre, rating, releasedDate, imageUrl)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {

        }

        return list
    }

    fun getTvShowData(): List<TvShowResponse> {
        var tvShowResponse: TvShowResponse?
        val list = ArrayList<TvShowResponse>()

        try {
            val obj = JSONObject(parsingFileToString("TvShow.json").toString())
            val listData = obj.getJSONArray("results")

            for (i in 0 until listData.length()) {
                val movie = listData.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val desc = movie.getString("desc")
                val genre = movie.getString("genres")
                val rating = movie.getString("rating")
                val releasedDate = movie.getString("released_date")
                val imageUrl = movie.getString("image_url")

                tvShowResponse =
                    TvShowResponse(id, title, desc, genre, rating, releasedDate, imageUrl)
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {

        }

        return list
    }


}