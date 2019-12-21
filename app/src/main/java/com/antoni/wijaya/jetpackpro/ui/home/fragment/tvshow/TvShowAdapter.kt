package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.data.model.MovieValue
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_shows.view.*

class TvShowAdapter(
    private val tvShows: ArrayList<TvShowEntity>,
    private val listener: (TvShowEntity) -> Unit
) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shows, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow= tvShows[position]
        holder.bind(tvShow)
        holder.itemView.setOnClickListener {
            listener(tvShow)
        }
    }


    class TvShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: TvShowEntity) {
            itemView.txt_title.text = movie.title
            itemView.txt_date.text = movie.releaseDate
            itemView.txt_genre.text = movie.genres
            Glide.with(itemView.context)
                .load(movie.imageUrl)
                .into(itemView.img_poster)
            itemView.rating_bar.rating = movie.rating!!.toFloat() / 20
        }

    }
}