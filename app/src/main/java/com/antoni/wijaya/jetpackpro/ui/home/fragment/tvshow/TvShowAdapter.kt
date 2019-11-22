package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.model.MovieEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_shows.view.*

class TvShowAdapter(
    private val movies: ArrayList<MovieEntity>,
    private val listener: (MovieEntity) -> Unit
) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shows, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, listener)
    }


    class TvShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: MovieEntity, listener: (MovieEntity) -> Unit) {
            itemView.txt_title.text = movie.title
            itemView.txt_date.text = movie.released_date
            itemView.txt_genre.text = movie.genres
            Glide.with(itemView.context)
                .load(movie.image)
                .into(itemView.img_poster)
            itemView.rating_bar.rating = movie.rating.toFloat() / 20

            itemView.setOnClickListener {
                listener(movie)
            }
        }

    }
}