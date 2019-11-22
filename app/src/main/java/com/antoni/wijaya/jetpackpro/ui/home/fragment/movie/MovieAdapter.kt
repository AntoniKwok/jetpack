package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.model.MovieEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_shows.view.*

class MovieAdapter(private val movies : ArrayList<MovieEntity>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

//    var movies : ArrayList<MovieEntity>
//        get() = movies
//        set(value) {
//            if(value == null) return
//            this.movies.clear()
//            this.movies.addAll(value)
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shows, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movies.get(position)
        holder.bind(movie)

    }


    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bind(movie : MovieEntity){
            itemView.txt_title.text = movie.title
            itemView.txt_date.text = movie.released_date
            itemView.txt_genre.text = movie.genres
            Glide.with(itemView.context)
                .load(movie.image)
                .into(itemView.img_poster)
        }

    }
}